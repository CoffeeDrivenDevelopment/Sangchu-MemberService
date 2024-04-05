package com.cdd.memberservice.module.follow.exception;

import com.cdd.common.exception.ErrorCode;
import com.cdd.common.exception.SangChuException;

public class FollowException extends SangChuException {

	public FollowException(ErrorCode errorCode) {
		super(errorCode);
	}
}
