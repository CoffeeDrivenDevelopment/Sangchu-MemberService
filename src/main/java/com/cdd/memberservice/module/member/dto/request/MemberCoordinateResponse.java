package com.cdd.memberservice.module.member.dto.request;

import com.cdd.memberservice.module.member.domain.Member;
import com.fasterxml.jackson.annotation.JsonProperty;

public record MemberCoordinateResponse(
	@JsonProperty("lng")
	double lng,
	@JsonProperty("lat")
	double lat
) {

	public static MemberCoordinateResponse from(Member member) {
		return new MemberCoordinateResponse(member.getLng(), member.getLat());
	}
}
