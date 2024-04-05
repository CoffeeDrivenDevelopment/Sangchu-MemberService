package com.cdd.memberservice.module.member.dto.request;

import com.fasterxml.jackson.annotation.*;


public record ProfileInfoRequest(
	@JsonProperty("member_id")
	Long memberId,
	@JsonProperty("nickname")
	String nickname,
	@JsonProperty("lat")
	Double lat,
	@JsonProperty("lng")
	Double lng

) {
}
