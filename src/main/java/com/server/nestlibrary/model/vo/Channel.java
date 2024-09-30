package com.server.nestlibrary.model.vo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class Channel {
	@Id
	@Column(name = "channel_code")
	private int channelCode;   // 채널코드

	@Column(name = "channel_name")
	private String channelName; // 채널명

	@Column(name = "channel_created_at")
	private LocalDateTime channelCreatedAt; // 채널 생성일

	@Column(name = "channel_img_url")
	private String channelImgUrl; // 채널 대표 이미지

}