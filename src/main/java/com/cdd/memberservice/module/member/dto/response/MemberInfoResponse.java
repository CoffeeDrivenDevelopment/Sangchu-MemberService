package com.cdd.memberservice.module.member.dto.response;

import com.cdd.memberservice.module.member.domain.Member;
import com.fasterxml.jackson.annotation.JsonProperty;

public record MemberInfoResponse(
	@JsonProperty("id")
	int id,
	@JsonProperty("profile_image")
	String image,
	@JsonProperty("nickname")
	String nickname
) {

	public static MemberInfoResponse from(Member member) {
		return new MemberInfoResponse(
			Math.toIntExact(member.getId()),
			member.getProfileImage(),
			member.getNickname()
		);
	}
}
