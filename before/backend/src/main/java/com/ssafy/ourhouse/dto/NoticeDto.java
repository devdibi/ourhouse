package com.ssafy.ourhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@ApiModel(value = "NoticeDto : 공지사항 정보", description = "공지사항의 상세 정보에 대한 Dto")
public class NoticeDto {

	@ApiModelProperty(value = "게시글 번호")
	private int noticeNo;

	@ApiModelProperty(value = "제목")
	private String title;

	@ApiModelProperty(value = "내용")
	private String content;
	
	@ApiModelProperty(value = "조회수")
	private int hit;

	@ApiModelProperty(value = "생성 날짜")
	private String createDate;

	@ApiModelProperty(value = "수정 날짜")
	private String updateDate;
	
	@ApiModelProperty(value = "글 작성자의 이메일")
	private String email;
	
	@Override
	public String toString() {
		return "NoticeDto [noticeNo=" + noticeNo + ", title=" + title + ", content=" + content + ", hit=" + hit
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", email=" + email + "]";
	}

}
