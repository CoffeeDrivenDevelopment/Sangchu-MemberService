package com.cdd.memberservice.module.follow.domain.query;

import static com.cdd.memberservice.module.follow.domain.QFollow.*;
import static com.cdd.memberservice.module.member.domain.QMember.*;

import java.util.List;
import java.util.Optional;

import com.cdd.memberservice.module.follow.domain.Follow;
import com.cdd.memberservice.module.follow.vo.Follower;
import com.cdd.memberservice.module.follow.vo.Following;
import com.cdd.memberservice.module.follow.vo.QFollower;
import com.cdd.memberservice.module.follow.vo.QFollowing;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FollowRepositoryCustomImpl implements FollowRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<Follow> findFollow(Long followerId, Long followingId) {
		return Optional.ofNullable(jpaQueryFactory.selectFrom(follow)
			.where(follow.follower.id.eq(followerId).and(follow.following.id.eq(followingId)))
			.fetchFirst());
	}

	@Override
	public List<Follower> findFollower(Long followingId) {
		return jpaQueryFactory.select(new QFollower(member.id, member.nickname, member.profileImage))
			.from(follow)
			.where(follow.following.id.eq(followingId))
			.join(follow.follower, member)
			.fetch();
	}

	@Override
	public List<Following> findFollowing(Long followerId) {
		return jpaQueryFactory.select(new QFollowing(member.id, member.nickname, member.profileImage))
			.from(follow)
			.where(follow.follower.id.eq(followerId))
			.join(follow.following, member)
			.fetch();
	}

	@Override
	public int findFollowerCountByMemberId(Long memberId) {
		return Math.toIntExact(jpaQueryFactory.select(follow.count())
			.from(follow)
			.where(follow.following.id.eq(memberId))
			.fetchFirst());
	}

	@Override
	public int findFollowingCountByMemberId(Long memberId) {
		return Math.toIntExact(jpaQueryFactory.select(follow.count())
			.from(follow)
			.where(follow.follower.id.eq(memberId))
			.fetchFirst());
	}
}
