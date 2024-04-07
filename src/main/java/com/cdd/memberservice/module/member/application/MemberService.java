package com.cdd.memberservice.module.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdd.memberservice.global.infra.storage.application.StorageClient;
import com.cdd.memberservice.module.follow.domain.FollowRepository;
import com.cdd.memberservice.module.follow.utils.FollowServiceUtils;
import com.cdd.memberservice.module.member.domain.Member;
import com.cdd.memberservice.module.member.domain.MemberRepository;
import com.cdd.memberservice.module.member.domain.MemberStats;
import com.cdd.memberservice.module.member.domain.MemberStatsRepository;
import com.cdd.memberservice.module.member.dto.request.MemberCoordinateResponse;
import com.cdd.memberservice.module.member.dto.request.ProfileImageRequest;
import com.cdd.memberservice.module.member.dto.request.ProfileInfoRequest;
import com.cdd.memberservice.module.member.dto.request.SigninRequest;
import com.cdd.memberservice.module.member.dto.response.MemberInfoResponse;
import com.cdd.memberservice.module.member.dto.response.NicknameResponse;
import com.cdd.memberservice.module.member.dto.response.ProfileResponse;
import com.cdd.memberservice.module.member.dto.response.SigninResponse;
import com.cdd.memberservice.module.member.exception.MemberErrorCode;
import com.cdd.memberservice.module.member.exception.MemberException;
import com.cdd.memberservice.module.member.utils.MemberServiceUtils;
import com.cdd.sangchupassport.Passport;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService implements MemberLoadService {
	private final StorageClient storageClient;
	private final FollowRepository followRepository;
	private final MemberRepository memberRepository;
	private final MemberStatsRepository memberStatsRepository;

	@Transactional
	public SigninResponse saveMember(SigninRequest request) {
		Member member = MemberServiceUtils.findbyOAuthId(memberRepository, request.oauthId())
			.orElseGet(() -> {
				Member newMember = memberRepository.save(Member.builder()
					.oauthId(request.oauthId())
					.build());
				memberStatsRepository.save(MemberStats.builder()
					.member(newMember)
					.build());
				return newMember;
			});
		return SigninResponse.from(member);
	}

	public ProfileResponse getMember(Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberException(MemberErrorCode.NO_SUCH_ELEMENT));
		int follower = FollowServiceUtils.findFollowerCountByMemberId(followRepository, member.getId());
		int following = FollowServiceUtils.findFollowingCountByMemberId(followRepository, member.getId());
		return ProfileResponse.of(member, follower, following);
	}

	@Transactional
	public ProfileResponse updateMemberInfo(
		final Passport passport,
		final ProfileInfoRequest request
	) {
		Member member = memberRepository.findById((long)passport.getMemberId())
			.orElseThrow(() -> new MemberException(MemberErrorCode.NO_SUCH_ELEMENT));
		member.updateMemberInfo(request);
		memberRepository.save(member);
		int follower = FollowServiceUtils.findFollowerCountByMemberId(followRepository, member.getId());
		int following = FollowServiceUtils.findFollowingCountByMemberId(followRepository, member.getId());
		return ProfileResponse.of(member, follower, following);
	}

	@Transactional
	public ProfileResponse updateMemberImage(ProfileImageRequest request) {
		Member member = memberRepository.findById(request.memberId())
			.orElseThrow(() -> new MemberException(MemberErrorCode.NO_SUCH_ELEMENT));
		member.updateMemberImage(storageClient.saveImage(request.profileImage()).fileSource());
		memberRepository.save(member);
		int follower = FollowServiceUtils.findFollowerCountByMemberId(followRepository, member.getId());
		int following = FollowServiceUtils.findFollowingCountByMemberId(followRepository, member.getId());
		return ProfileResponse.of(member, follower, following);
	}

	public NicknameResponse validateNickname(String nickname) {
		Member member = MemberServiceUtils.findByNickname(memberRepository, nickname)
			.orElse(Member.builder().build());
		return NicknameResponse.builder()
			.usable(member.getNickname() != null)
			.build();
	}

	@Override
	public MemberInfoResponse findMemberInfo(final int memberId) {
		Member findMember = MemberServiceUtils.findById(memberRepository, (long)memberId);

		return MemberInfoResponse.from(findMember);
	}

	@Override
	public MemberCoordinateResponse findMemberCoordinate(final Passport passport) {
		int memberId = passport.getMemberId();
		Member findMember = MemberServiceUtils.findById(memberRepository, (long)memberId);

		return MemberCoordinateResponse.from(findMember);
	}
}