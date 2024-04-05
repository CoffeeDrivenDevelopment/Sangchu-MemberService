package com.cdd.memberservice.module.follow.dto.response;

import java.util.List;

import com.cdd.memberservice.module.follow.vo.Follower;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record FollowerResponse(
	@JsonProperty("followers")
	List<Follower> followerList
) {
	public static FollowerResponse from(List<Follower> followerList) {
		return FollowerResponse.builder()
			.followerList(followerList)
			.build();
	}
}
