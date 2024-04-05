package com.cdd.memberservice.module.follow.domain.query;

import java.util.List;
import java.util.Optional;

import com.cdd.memberservice.module.follow.domain.Follow;
import com.cdd.memberservice.module.follow.vo.Follower;
import com.cdd.memberservice.module.follow.vo.Following;

public interface FollowRepositoryCustom {
	Optional<Follow> findFollow(Long followerId, Long followingId);

	List<Follower> findFollower(Long followingId);

	List<Following> findFollowing(Long followerId);

	int findFollowerCountByMemberId(Long memberId);

	int findFollowingCountByMemberId(Long memberId);
}
