package com.cdd.memberservice.module.follow.exception;

import com.cdd.common.exception.ErrorCode;

import lombok.Getter;

@Getter
public enum FollowErrorCode implements ErrorCode {
	BAD_REQUEST(400, "FOLLOW_001", "잘못된 요청입니다."),
	UNAUTHORIZED(401, "FOLLOW_002", "허가되지 않은 요청입니다."),
	NO_SUCH_ELEMENT(404, "FOLLOW_003", "요청한 정보가 존재하지 않습니다."),
	INTERNAL_SERVER_ERROR(500, "FOLLOW_004", "내부 서버 오류입니다.");

	private final int statusCode;
	private final String errorCode;
	private final String message;

	private FollowErrorCode(int statusCode, String errorCode, String message) {
		this.statusCode = statusCode;
		this.errorCode = errorCode;
		this.message = message;
	}
}
