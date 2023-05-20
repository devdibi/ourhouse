package com.ssafy.ourhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value="NewsDto : 뉴스 게시판", description="뉴스 기사의 Dto")
public class NewsDto {
	
	@ApiModelProperty(value="뉴스의 글 번호")
	private int newsNo;

	@ApiModelProperty(value="뉴스의 제목")
	private String title;
	
	@ApiModelProperty(value="뉴스의 본문 앞부분")
	private String context;
	
	@ApiModelProperty(value="뉴스의 링크")
	private String link;
	
	@ApiModelProperty(value="뉴스의 작성일시")
	private String postDate;
	
	@ApiModelProperty(value="뉴스의 조회수(서비스 내 접속 횟수)")
	private int hit;

	@Override
	public String toString() {
		return "NewsDto [newsNo=" + newsNo + ", title=" + title + ", context=" + context + ", link=" + link
				+ ", postDate=" + postDate + ", hit=" + hit + "]";
	}
	
}
