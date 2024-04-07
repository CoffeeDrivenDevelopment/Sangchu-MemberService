package com.cdd.memberservice.module.member.utils;

import java.util.Optional;

import com.cdd.memberservice.module.member.domain.Member;
import com.cdd.memberservice.module.member.domain.MemberRepository;
import com.cdd.memberservice.module.member.exception.MemberErrorCode;
import com.cdd.memberservice.module.member.exception.MemberException;

public class MemberServiceUtils {

	public static Member findById(MemberRepository memberRepository, Long memberId) {
		return memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberException(MemberErrorCode.NO_SUCH_ELEMENT));
	}

	public static Optional<Member> findbyOAuthId(MemberRepository memberRepository, String oauthId) {
		return memberRepository.findByOAuthId(oauthId);
	}

	public static Optional<Member> findByNickname(MemberRepository memberRepository, String nickname) {
		return memberRepository.findByNickname(nickname);
	}
}
