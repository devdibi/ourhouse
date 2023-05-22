package com.ssafy.ourhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "LikeDto : 좋아요 정보를 주고받을때 사용할 Dto")
public class LikeDto {

//	@ApiModelProperty(value = "유저의 JWT")
//	private String jwtToken;

	@ApiModelProperty(value = "거래/아파트 코드")
	private String code;

	@Override
	public String toString() {
		return "LikeDto [code=" + code + "]";
	}

//	@Override
//	public String toString() {
//		return "LikeDto [jwtToken=" + jwtToken + ", code=" + code + "]";
//	}

}
