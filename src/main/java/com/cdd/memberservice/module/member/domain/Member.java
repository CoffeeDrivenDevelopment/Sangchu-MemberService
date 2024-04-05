package com.cdd.memberservice.module.member.domain;

import org.hibernate.validator.constraints.*;

import com.cdd.memberservice.global.domain.*;
import com.cdd.memberservice.module.member.dto.request.*;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_tbl", uniqueConstraints = {@UniqueConstraint(name = "OAUTH_ID_UNIQUE", columnNames = "oauth_id")})
@Entity
public class Member extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@NonNull
	@Column(name = "oauth_id")
	private String oauthId;

	@Length(max = 16)
	@Column(name = "nickname")
	private String nickname;

	@Length(max = 255)
	@Column(name = "profile_image")
	private String profileImage;

	@Column(name = "lat")
	private Double lat;

	@Column(name = "lng")
	private Double lng;

	@Builder
	public Member(String oauthId, String nickname, String profileImage, Double lat, Double lng) {
		this.oauthId = oauthId;
		this.nickname = nickname;
		this.profileImage = profileImage;
		this.lat = lat;
		this.lng = lng;
	}

	public void updateMemberInfo(ProfileInfoRequest request) {
		this.nickname = (request.nickname() == null ? this.nickname : request.nickname());
		this.lat = (request.lat() == null ? this.lat : request.lat());
		this.lng = (request.lng() == null ? this.lng : request.lng());
	}

	public void updateMemberImage(String profileImage) {
		this.profileImage = (profileImage == null ? this.nickname : profileImage);
	}
}
