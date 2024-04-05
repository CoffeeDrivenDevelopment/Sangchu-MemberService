package com.cdd.memberservice.module.member.application;

import com.cdd.memberservice.module.member.dto.response.MemberInfoResponse;

public interface MemberLoadService {
	MemberInfoResponse findMemberInfo(int memberId);
}
