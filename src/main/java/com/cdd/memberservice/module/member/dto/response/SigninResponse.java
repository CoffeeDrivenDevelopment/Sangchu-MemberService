package com.cdd.memberservice.module.member.dto.response;

import com.cdd.memberservice.module.member.domain.Member;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record SigninResponse(
        @JsonProperty("member_id")
        Long memberId,
        @JsonProperty("nickname")
        String nickname,
        @JsonProperty("profile_image")
        String profileImage
) {
    public static SigninResponse from(Member member) {
        return SigninResponse.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .profileImage(member.getProfileImage())
                .build();
    }
}
