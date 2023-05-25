package com.ssafy.ourhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "SigunguDto : 시군구 정보에 대한 Dto")
public class SigunguDto {
	
	@ApiModelProperty(value = "시군구 코드, 동 코드의 앞 5글자")
	private String sigunguCode;

	@ApiModelProperty(value = "시군구 이름")
	private String name;
	
	@ApiModelProperty(value = "해당 시군구의 시도 코드 (시군구 코드의 앞 2글자)")
	private String sidoCode;

	@Override
	public String toString() {
		return "SigunguDto [sigunguCode=" + sigunguCode + ", name=" + name + ", sidoCode=" + sidoCode + "]";
	}
	
}
