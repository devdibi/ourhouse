package com.ssafy.ourhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value= "CommentDto : 게시판 댓글", description="게시판의 상세 정보에 대한 Dto")
public class CommentDto {
	
	@ApiModelProperty(value="댓글 번호")
	private int commentNo;
	
	@ApiModelProperty(value="게시글 번호")
	private int BoardNo;
	
	@ApiModelProperty(value="댓글")
	private String comment;
	
	@ApiModelProperty(value="작성자")
	private String email;
	
	@ApiModelProperty(value="작성 시간")
	private String createDate;
	
	@ApiModelProperty(value="게시글 내 댓글 번호")
	private int commentId;

	@Override
	public String toString() {
		return "CommentDto [commentNo=" + commentNo + ", BoardNo=" + BoardNo + ", comment=" + comment + ", email="
				+ email + ", createDate=" + createDate + ", commentId=" + commentId + "]";
	}
	
	
}
