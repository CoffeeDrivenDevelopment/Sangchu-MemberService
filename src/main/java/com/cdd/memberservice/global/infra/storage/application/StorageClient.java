package com.cdd.memberservice.global.infra.storage.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.cdd.memberservice.global.infra.storage.dto.response.StorageResponse;

@Component
public class StorageClient {
	@Value("${url.storage-service}")
	private String STORAGE_URL;

	public StorageResponse saveImage(MultipartFile image) {
		if (image == null) {
			return StorageResponse.builder()
				.fileSource(null)
				.build();
		}
		MultipartBodyBuilder builder = new MultipartBodyBuilder();
		builder.part("file_source", image.getResource());
		return WebClient.create(STORAGE_URL)
			.post()
			.uri("/v1/file")
			.contentType(MediaType.MULTIPART_FORM_DATA)
			.body(BodyInserters.fromMultipartData(builder.build()))
			.retrieve()
			.bodyToMono(StorageResponse.class)
			.block();
	}
}
