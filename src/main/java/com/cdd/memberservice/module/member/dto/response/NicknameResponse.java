package com.cdd.memberservice.module.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record NicknameResponse(
        @JsonProperty("usable")
        boolean usable
) {
}
