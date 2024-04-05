package com.cdd.memberservice.module.follow.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdd.memberservice.module.follow.domain.query.FollowRepositoryCustom;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowRepositoryCustom {
}
