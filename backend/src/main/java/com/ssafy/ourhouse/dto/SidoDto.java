package com.ssafy.ourhouse.dto;

import java.util.ArrayList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "SidoDto : 시/도 정보에 대한 Dto ", description = "시/도 정보를 다루는 Dto")
public class SidoDto {
	
	@ApiModelProperty(value = "시/도 이름")
	private String sidoCode;
	
	@ApiModelProperty(value = "시/도 코드, 동코드의 맨 앞 2자리")
	private String name;

	@Override
	public String toString() {
		return "SidoDto [sidoCode=" + sidoCode + ", name=" + name + "]";
	}
	
}
