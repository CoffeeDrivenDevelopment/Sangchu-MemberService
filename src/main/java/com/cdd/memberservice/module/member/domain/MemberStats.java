package com.cdd.memberservice.module.member.domain;

import com.cdd.memberservice.global.domain.BaseTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_stats_tbl")
@Entity
public class MemberStats extends BaseTime {
	@Id
	private Long id;

	@MapsId
	@JoinColumn(name = "member_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Member member;

	private int followerCount;
	private int followingCount;

	private int recipeLikeCount;
	private int commentCount;
	private int cookEatCount;

	@Builder
	public MemberStats(Member member) {
		this.member = member;
	}

	public void followerCountUp() {
		this.followerCount++;
	}

	public void followerCountDown() {
		this.followerCount--;
	}

	public void followingCountUp() {
		this.followingCount++;
	}

	public void followingCountDown() {
		this.followingCount--;
	}

	public void recipeLikeCountUp() {
		this.recipeLikeCount++;
	}

	public void recipeLikeCountDown() {
		this.recipeLikeCount--;
	}

	public void commentCountUp() {
		this.commentCount++;
	}

	public void commentCountDown() {
		this.commentCount--;
	}

	public void cookEatCountUp() {
		this.cookEatCount++;
	}

	public void cookEatCountDown() {
		this.cookEatCount--;
	}
}
