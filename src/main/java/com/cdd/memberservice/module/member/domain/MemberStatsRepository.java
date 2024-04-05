package com.cdd.memberservice.module.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberStatsRepository extends JpaRepository<MemberStats, Long> {
}
