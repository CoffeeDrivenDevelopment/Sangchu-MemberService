package com.cdd.memberservice.module.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MemberCoordinateResponse(
	@JsonProperty("lng")
	double lng,
	@JsonProperty("lat")
	double lat
) {
}
