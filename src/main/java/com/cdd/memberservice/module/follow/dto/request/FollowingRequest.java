package com.cdd.memberservice.module.follow.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FollowingRequest(
	@JsonProperty("auth_id")
	Long authId,
	@JsonProperty("member_id")
	Long memberId
) {
}
