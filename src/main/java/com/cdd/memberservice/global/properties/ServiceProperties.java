package com.cdd.memberservice.global.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "service")
public class ServiceProperties {
	private String baseUrl;
	private String storage;
}
