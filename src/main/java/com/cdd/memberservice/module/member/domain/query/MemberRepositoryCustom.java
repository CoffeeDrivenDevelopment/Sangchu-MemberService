package com.cdd.memberservice.module.member.domain.query;

import java.util.Optional;

import com.cdd.memberservice.module.member.domain.Member;

public interface MemberRepositoryCustom {
	Optional<Member> findByOAuthId(String oauthId);

	Optional<Member> findByNickname(String nickname);
}
