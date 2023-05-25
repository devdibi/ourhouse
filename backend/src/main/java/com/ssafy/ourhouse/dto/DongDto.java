package com.ssafy.ourhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "DongDto : 동 정보에 대한 Dto")
public class DongDto {
	
	@ApiModelProperty(value = "동 코드, 10글자")
	private String dongCode;

	@ApiModelProperty(value = "동 이름")
	private String name;

	@ApiModelProperty(value = "시군구 코드 (동 코드의 앞 5글자)")
	private String sigunguCode;

	
	@ApiModelProperty(value = "시도 코드 (동 코드의 앞 2글자)")
	private String sidoCode;

}
