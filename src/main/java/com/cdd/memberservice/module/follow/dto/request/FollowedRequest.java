package com.cdd.memberservice.module.follow.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FollowedRequest(
	@JsonProperty("follower")
	Long follower,
	@JsonProperty("following")
	Long following
) {
}
