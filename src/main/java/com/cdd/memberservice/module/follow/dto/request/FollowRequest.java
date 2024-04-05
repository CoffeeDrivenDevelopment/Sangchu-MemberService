package com.cdd.memberservice.module.follow.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FollowRequest(
	@JsonProperty("follower")
	Long followerId,
	@JsonProperty("following")
	Long followingId
) {
}
