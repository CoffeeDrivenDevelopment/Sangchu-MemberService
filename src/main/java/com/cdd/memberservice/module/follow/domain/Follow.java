package com.cdd.memberservice.module.follow.domain;

import com.cdd.memberservice.global.domain.BaseTime;
import com.cdd.memberservice.module.member.domain.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "follow_tbl")
@Entity
public class Follow extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "follow_id")
	private Long id;

	@NonNull
	@JoinColumn(name = "follwer_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Member follower;

	@NonNull
	@JoinColumn(name = "following_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Member following;

	@Builder
	public Follow(@NonNull Member follower, @NonNull Member following) {
		this.follower = follower;
		this.following = following;
	}
}
