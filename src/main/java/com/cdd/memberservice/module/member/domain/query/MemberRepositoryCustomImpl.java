package com.cdd.memberservice.module.member.domain.query;

import static com.cdd.memberservice.module.member.domain.QMember.*;

import java.util.Optional;

import com.cdd.memberservice.module.member.domain.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<Member> findByOAuthId(String oAuthId) {
		return Optional.ofNullable(jpaQueryFactory
			.selectFrom(member)
			.where(member.oauthId.eq(oAuthId))
			.fetchFirst());
	}

	@Override
	public Optional<Member> findByNickname(String nickname) {
		return Optional.ofNullable(jpaQueryFactory
			.selectFrom(member)
			.where(member.nickname.eq(nickname))
			.fetchFirst());
	}
}
