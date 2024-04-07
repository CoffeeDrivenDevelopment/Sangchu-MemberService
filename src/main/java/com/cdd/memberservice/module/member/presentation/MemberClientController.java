package com.cdd.memberservice.module.member.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdd.memberservice.module.member.application.MemberLoadService;
import com.cdd.memberservice.module.member.dto.request.MemberCoordinateResponse;
import com.cdd.memberservice.module.member.dto.response.MemberInfoResponse;
import com.cdd.sangchupassport.Passport;
import com.cdd.sangchupassport.support.RequestPassport;

import lombok.RequiredArgsConstructor;

@RequestMapping("/member-service")
@RequiredArgsConstructor
@RestController
public class MemberClientController {
	private final MemberLoadService memberLoadService;

	@GetMapping("/v1/members/{member_id}")
	public ResponseEntity<MemberInfoResponse> findMemberInfo(
		@RequestPassport Passport passport,
		@PathVariable("member_id") int memberId
	) {
		MemberInfoResponse response = memberLoadService.findMemberInfo(memberId);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/v1/members/coordinate")
	public ResponseEntity<MemberCoordinateResponse> findMemberCoordinate(
		@RequestPassport Passport passport
	) {
		MemberCoordinateResponse response = memberLoadService.findMemberCoordinate(passport);
		return ResponseEntity.ok(response);
	}
}
