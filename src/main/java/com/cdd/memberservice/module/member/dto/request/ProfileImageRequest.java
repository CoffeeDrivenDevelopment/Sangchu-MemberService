package com.cdd.memberservice.module.member.dto.request;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record ProfileImageRequest(
	@JsonProperty("member_id")
	Long memberId,
	@JsonProperty("profile_image")
	MultipartFile profileImage
) {
}
