package com.cdd.memberservice.module.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SigninRequest(
        @JsonProperty("oauth_id")
        String oauthId
) {
}
