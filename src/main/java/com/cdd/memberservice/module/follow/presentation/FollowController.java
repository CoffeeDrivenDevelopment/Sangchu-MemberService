package com.cdd.memberservice.module.follow.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdd.common.response.MessageBody;
import com.cdd.common.response.ResponseEntityFactory;
import com.cdd.memberservice.module.follow.application.FollowService;
import com.cdd.memberservice.module.follow.dto.request.FollowRequest;
import com.cdd.memberservice.module.follow.dto.request.FollowedRequest;
import com.cdd.memberservice.module.follow.dto.request.FollowerRequest;
import com.cdd.memberservice.module.follow.dto.request.FollowingRequest;
import com.cdd.memberservice.module.follow.dto.response.FollowedResponse;
import com.cdd.memberservice.module.follow.dto.response.FollowerResponse;
import com.cdd.memberservice.module.follow.dto.response.FollowingResponse;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/member-service")
@RestController
public class FollowController {
	private final FollowService followService;

	@PostMapping("/v1/follow")
	public ResponseEntity<MessageBody<Void>> follow(
		@RequestBody FollowRequest request
	) {
		followService.follow(request);
		return ResponseEntityFactory.ok("팔로우 성공");
	}

	@DeleteMapping("/v1/follow")
	public ResponseEntity<MessageBody<Void>> unfollow(
		@RequestBody FollowRequest request
	) {

		followService.unfollow(request);
		return ResponseEntityFactory.ok("언팔로우 성공");
	}

	@PostMapping("/v1/follower")
	public ResponseEntity<MessageBody<FollowerResponse>> findfollower(
		@RequestBody FollowerRequest request
	) {
		return ResponseEntityFactory.ok("팔로워 조회 성공", followService.findFollower(request));
	}

	@PostMapping("/v1/following")
	public ResponseEntity<MessageBody<FollowingResponse>> findFollowing(
		@RequestBody FollowingRequest request
	) {
		return ResponseEntityFactory.ok("팔로잉 조회 성공", followService.findFollowing(request));
	}

	@PostMapping("/v1/followed")
	public ResponseEntity<MessageBody<FollowedResponse>> findFollowed(@RequestBody FollowedRequest request) {
		return ResponseEntityFactory.ok("팔로우 조회 성공", followService.findFollowed(request));
	}
}
