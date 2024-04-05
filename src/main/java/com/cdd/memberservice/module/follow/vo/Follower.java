package com.cdd.memberservice.module.follow.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class Follower {
	@JsonProperty("member_id")
	private Long memberId;
	@JsonProperty("nickname")
	private String nickname;
	@JsonProperty("profile_image")
	private String profileImage;
	@JsonProperty("followed")
	private boolean followed;

	@QueryProjection
	public Follower(Long memberId, String nickname, String profileImage) {
		this.memberId = memberId;
		this.nickname = nickname;
		this.profileImage = profileImage;
	}

	public void updateFollowed(boolean followed) {
		this.followed = followed;
	}
}
