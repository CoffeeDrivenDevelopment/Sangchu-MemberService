package com.cdd.memberservice.module.follow.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record FollowedResponse(
	@JsonProperty("followed")
	boolean followed
) {
	public static FollowedResponse from(Boolean followed) {
		return FollowedResponse.builder()
			.followed(followed)
			.build();
	}
}
