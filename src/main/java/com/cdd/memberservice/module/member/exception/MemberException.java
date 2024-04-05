package com.cdd.memberservice.module.member.exception;

import com.cdd.common.exception.ErrorCode;
import com.cdd.common.exception.SangChuException;

public class MemberException extends SangChuException {

	public MemberException(ErrorCode errorCode) {
		super(errorCode);
	}
}
