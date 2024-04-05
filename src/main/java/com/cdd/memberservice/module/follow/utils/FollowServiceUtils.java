package com.cdd.memberservice.module.follow.utils;

import java.util.List;
import java.util.Optional;

import com.cdd.memberservice.module.follow.domain.Follow;
import com.cdd.memberservice.module.follow.domain.FollowRepository;
import com.cdd.memberservice.module.follow.vo.Follower;
import com.cdd.memberservice.module.follow.vo.Following;

public class FollowServiceUtils {
	public static Optional<Follow> findFollow(FollowRepository followRepository, Long followerId, Long followingId) {
		return followRepository.findFollow(followerId, followingId);
	}

	public static List<Follower> findFollowerByFollowingId(FollowRepository followRepository, Long memberId) {
		return followRepository.findFollower(memberId);
	}

	public static List<Following> findFollowingByFollowerId(FollowRepository followRepository, Long memberId) {
		return followRepository.findFollowing(memberId);
	}

	public static int findFollowerCountByMemberId(FollowRepository followRepository, Long memberId) {
		return followRepository.findFollowerCountByMemberId(memberId);
	}

	public static int findFollowingCountByMemberId(FollowRepository followRepository, Long memberId) {
		return followRepository.findFollowingCountByMemberId(memberId);
	}
}
