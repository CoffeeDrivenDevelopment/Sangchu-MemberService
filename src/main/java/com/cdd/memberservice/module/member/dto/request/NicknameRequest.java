package com.cdd.memberservice.module.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NicknameRequest(
	@JsonProperty("nickname")
	String nickname
) {
}
