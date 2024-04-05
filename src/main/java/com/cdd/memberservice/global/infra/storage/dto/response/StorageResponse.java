package com.cdd.memberservice.global.infra.storage.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record StorageResponse(
	@JsonProperty("file_source")
	String fileSource
) {
}
