package com.cdd.memberservice.module.follow.dto.response;

import java.util.List;

import com.cdd.memberservice.module.follow.vo.Following;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record FollowingResponse(
	@JsonProperty("followings")
	List<Following> followingList
) {
	public static FollowingResponse from(List<Following> followingList) {
		return FollowingResponse.builder()
			.followingList(followingList)
			.build();
	}
}
