package com.cdd.memberservice.module.member.application;

import com.cdd.memberservice.module.member.dto.request.MemberCoordinateResponse;
import com.cdd.memberservice.module.member.dto.response.MemberInfoResponse;
import com.cdd.sangchupassport.Passport;

public interface MemberLoadService {
	MemberInfoResponse findMemberInfo(int memberId);

	MemberCoordinateResponse findMemberCoordinate(Passport passport);
}
