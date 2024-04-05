package com.cdd.memberservice.global.infra.storage.dto.request;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record StorageRequest(
	@JsonProperty("file_source")
	MultipartFile fileSource
) {
}
