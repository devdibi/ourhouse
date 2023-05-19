package com.ssafy.ourhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "DealDto : 거래 정보에 대한 Dto ", 
description = "거래 정보를 알려주는 Dto, HouseDto 내에 List 형태로 들어감")
public class DealDto {
	
	@ApiModelProperty(value = "거래 코드")
	private String dealCode;

	@ApiModelProperty(value = "거래 가격")
	private String price;

	@ApiModelProperty(value = "거래 년도")
	private String year;

	@ApiModelProperty(value = "거래 월")
	private String month;

	@ApiModelProperty(value = "면적")
	private String area;

	@ApiModelProperty(value = "층수")
	private String floor;
	
	@ApiModelProperty(value = "거래 좋아요 여부")
	private boolean dealLike;
	
	@Override
	public String toString() {
		return "DealDto [dealCode=" + dealCode + ", price=" + price + ", year=" + year + ", month=" + month + ", area="
				+ area + ", floor=" + floor + ", dealLike=" + dealLike + "]";
	}

	@Override
	public boolean equals(Object o) {
		if(o == null || getClass() != o.getClass()) return false;
		if(this == o) return true;
		DealDto tmp = (DealDto) o;
		return tmp.dealCode.equals(this.dealCode);
	}
}
