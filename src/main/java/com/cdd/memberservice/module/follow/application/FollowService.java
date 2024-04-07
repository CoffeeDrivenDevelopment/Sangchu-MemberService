package com.cdd.memberservice.module.follow.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdd.memberservice.module.follow.domain.Follow;
import com.cdd.memberservice.module.follow.domain.FollowRepository;
import com.cdd.memberservice.module.follow.dto.request.FollowRequest;
import com.cdd.memberservice.module.follow.dto.request.FollowedRequest;
import com.cdd.memberservice.module.follow.dto.request.FollowerRequest;
import com.cdd.memberservice.module.follow.dto.request.FollowingRequest;
import com.cdd.memberservice.module.follow.dto.response.FollowedResponse;
import com.cdd.memberservice.module.follow.dto.response.FollowerResponse;
import com.cdd.memberservice.module.follow.dto.response.FollowingResponse;
import com.cdd.memberservice.module.follow.exception.FollowErrorCode;
import com.cdd.memberservice.module.follow.exception.FollowException;
import com.cdd.memberservice.module.follow.utils.FollowServiceUtils;
import com.cdd.memberservice.module.follow.vo.Follower;
import com.cdd.memberservice.module.follow.vo.Following;
import com.cdd.memberservice.module.member.domain.MemberRepository;
import com.cdd.memberservice.module.member.domain.MemberStats;
import com.cdd.memberservice.module.member.domain.MemberStatsRepository;
import com.cdd.memberservice.module.member.utils.MemberServiceUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FollowService {
	private final FollowRepository followRepository;
	private final MemberRepository memberRepository;
	private final MemberStatsRepository memberStatsRepository;

	@Transactional
	public void follow(FollowRequest request) {
		if (followRepository.findFollow(request.followerId(), request.followingId()).isPresent()) {
			throw new FollowException(FollowErrorCode.BAD_REQUEST);
		}
		followRepository.save(Follow.builder()
			.follower(MemberServiceUtils.findById(memberRepository, request.followerId()))
			.following(MemberServiceUtils.findById(memberRepository, request.followingId()))
			.build());
		MemberStats followerStats = memberStatsRepository.findById(request.followerId())
			.orElseThrow(() -> new FollowException(FollowErrorCode.NO_SUCH_ELEMENT));
		followerStats.followingCountUp();
		memberStatsRepository.save(followerStats);
		MemberStats followingStats = memberStatsRepository.findById(request.followingId())
			.orElseThrow(() -> new FollowException(FollowErrorCode.NO_SUCH_ELEMENT));
		followingStats.followerCountUp();
		memberStatsRepository.save(followingStats);
	}

	@Transactional
	public void unfollow(FollowRequest request) {
		Follow follow = FollowServiceUtils.findFollow(followRepository, request.followerId(), request.followingId())
			.orElseThrow(() -> new FollowException(FollowErrorCode.NO_SUCH_ELEMENT));
		followRepository.delete(follow);
		MemberStats followerStats = memberStatsRepository.getReferenceById(request.followerId());
		followerStats.followingCountDown();
		memberStatsRepository.save(followerStats);
		MemberStats followingStats = memberStatsRepository.getReferenceById(request.followingId());
		followingStats.followerCountDown();
		memberStatsRepository.save(followingStats);
	}

	public FollowerResponse findFollower(FollowerRequest request) {
		List<Follower> followerList = FollowServiceUtils.findFollowerByFollowingId(followRepository,
			request.memberId());
		for (Follower follower : followerList) {
			follower.updateFollowed(
				FollowServiceUtils.findFollow(followRepository,
					request.authId(), follower.getMemberId()).isPresent());
		}
		return FollowerResponse.from(followerList);
	}

	public FollowingResponse findFollowing(FollowingRequest request) {
		List<Following> followingList = FollowServiceUtils.findFollowingByFollowerId(followRepository,
			request.memberId());
		for (Following following : followingList) {
			following.updateFollowed(
				FollowServiceUtils.findFollow(followRepository,
					request.authId(), following.getMemberId()).isPresent());
		}
		return FollowingResponse.from(followingList);
	}

	public FollowedResponse findFollowed(FollowedRequest request) {
		return FollowedResponse.from(
			FollowServiceUtils.findFollow(followRepository, request.follower(), request.following())
				.isPresent());
	}
}
