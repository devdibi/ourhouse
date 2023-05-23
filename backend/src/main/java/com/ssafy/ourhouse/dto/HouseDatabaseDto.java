package com.ssafy.ourhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "HouseDatabaseDto : DB에서 거래 정보를 받아올 때 쓰는 Dto", 
	description = "DB에서 house_info와 house_deal을 join해 한꺼번에 받아올 때 쓰는 Dto, 서비스단까지만 쓰임")
public class HouseDatabaseDto {

	@ApiModelProperty(value = "아파트 코드")
	private String aptCode;

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

	@ApiModelProperty(value = "지은 년도")
	private String buildYear;

	@ApiModelProperty(value = "아파트 이름")
	private String name;

	@ApiModelProperty(value = "지번 주소")
	private String jibunAddress;

	@ApiModelProperty(value = "도로명 주소")
	private String roadAddress;

	@ApiModelProperty(value = "위도")
	private String lng;

	@ApiModelProperty(value = "경도")
	private String lat;

	@ApiModelProperty(value = "동 코드")
	private String dongCode;

	@ApiModelProperty(value = "평균 가격")
	private String priceAvg;

	@ApiModelProperty(value = "평균 면적")
	private String areaAvg;

	@Override
	public String toString() {
		return "HouseDatabaseDto [aptCode=" + aptCode + ", dealCode=" + dealCode + ", price=" + price + ", year=" + year
				+ ", month=" + month + ", area=" + area + ", floor=" + floor + ", buildYear=" + buildYear + ", name="
				+ name + ", jibunAddress=" + jibunAddress + ", roadAddress=" + roadAddress + ", lng=" + lng + ", lat="
				+ lat + ", dongCode=" + dongCode + "]";
	}

}
