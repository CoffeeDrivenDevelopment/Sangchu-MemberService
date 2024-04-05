package com.cdd.memberservice.module.member.dto.response;

import com.cdd.memberservice.module.member.domain.*;
import com.fasterxml.jackson.annotation.*;

import lombok.*;

@Builder
public record ProfileResponse(
	@JsonProperty("member_id")
	Long memberId,
	@JsonProperty("nickname")
	String nickname,
	@JsonProperty("lat")
	Double lat,
	@JsonProperty("lng")
	Double lng,
	@JsonProperty("profile_image")
	String profileImage,
	@JsonProperty("follower")
	int follower,
	@JsonProperty("following")
	int following
) {
	public static ProfileResponse of(Member member, int follower, int following) {
		return ProfileResponse.builder()
			.memberId(member.getId())
			.nickname(member.getNickname())
			.lat(member.getLat())
			.lng((member.getLng()))
			.profileImage(member.getProfileImage())
			.follower(follower)
			.following(following)
			.build();
	}
}