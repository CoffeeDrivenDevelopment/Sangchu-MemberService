package com.cdd.memberservice.module.member.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cdd.common.response.MessageBody;
import com.cdd.common.response.ResponseEntityFactory;
import com.cdd.memberservice.module.member.application.MemberService;
import com.cdd.memberservice.module.member.dto.request.ProfileImageRequest;
import com.cdd.memberservice.module.member.dto.request.ProfileInfoRequest;
import com.cdd.memberservice.module.member.dto.request.SigninRequest;
import com.cdd.memberservice.module.member.dto.response.NicknameResponse;
import com.cdd.memberservice.module.member.dto.response.ProfileResponse;
import com.cdd.memberservice.module.member.dto.response.SigninResponse;
import com.cdd.sangchupassport.Passport;
import com.cdd.sangchupassport.support.RequestPassport;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequestMapping("/member-service")
@RequiredArgsConstructor
@RestController
public class MemberController {
	private final MemberService memberService;

	@PostMapping("/v1/signin")
	public ResponseEntity<SigninResponse> saveMember(@RequestBody SigninRequest request) {
		return ResponseEntity.ok(memberService.saveMember(request));
	}

	@GetMapping("/v1/profile/{member_id}")
	public ResponseEntity<MessageBody<ProfileResponse>> getMypage(@PathVariable(name = "member_id") Long memberId) {
		return ResponseEntityFactory.ok("회원 정보 조회 성공", memberService.getMember(memberId));
	}

	@PatchMapping("/v1/profile")
	public ResponseEntity<MessageBody<ProfileResponse>> updateMemberInfo(
		@RequestPassport Passport passport,
		@RequestBody ProfileInfoRequest request
	) {
		return ResponseEntityFactory.ok("회원 정보 수정 성공",
			memberService.updateMemberInfo(passport, request));
	}

	@PatchMapping("/v1/profile/image")
	public ResponseEntity<MessageBody<ProfileResponse>> updateMemberImage(
		@RequestParam(value = "member_id") Long memberId,
		@RequestParam(value = "profile_image", required = false) MultipartFile profileImage) {
		return ResponseEntityFactory.ok("회원 이미지 수정 성공", memberService.updateMemberImage(
			ProfileImageRequest.builder().memberId(memberId).profileImage(profileImage).build()));
	}

	@GetMapping("/v1/validate/{nickname}")
	public ResponseEntity<MessageBody<NicknameResponse>> validateNickname(
		@RequestPassport Passport passport,
		@PathVariable String nickname
	) {
		return ResponseEntityFactory.ok("닉네임 조회 성공", memberService.validateNickname(nickname));
	}
}
