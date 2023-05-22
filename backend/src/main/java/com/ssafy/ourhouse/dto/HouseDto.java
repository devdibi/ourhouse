package com.ssafy.ourhouse.dto;

import java.util.ArrayList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "HouseDto : 아파트 정보에 대한 Dto ", 
description = "거래 정보를 제외한, 아파트 정보를 알려주는 Dto")
public class HouseDto {
	
	@ApiModelProperty(value = "아파트 코드")
	private String aptCode;

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
	
	@ApiModelProperty(value = "아파트 좋아요 여부")
	private boolean houseLike;
	
	@ApiModelProperty(value = "거래 정보를 저장할 리스트")
	private ArrayList<DealDto> deals;

	@ApiModelProperty(value = "평균 가격")
	private String priceAvg;

	@ApiModelProperty(value = "평균 면적")
	private String areaAvg;

	public HouseDto() {
		super();
		deals = new ArrayList<DealDto>();
	}

	// 비교할때 쓰기 위한 임시 생성자 
	public HouseDto(String aptCode) {
		super();
		this.aptCode = aptCode;
	}
	
	public void addDeal(DealDto tmp) {
		this.deals.add(tmp);
	}

	@Override
	public String toString() {
		return "HouseDto [aptCode=" + aptCode + ", buildYear=" + buildYear + ", name=" + name + ", jibunAddress="
				+ jibunAddress + ", roadAddress=" + roadAddress + ", lng=" + lng + ", lat=" + lat + ", dongCode="
				+ dongCode + ", houseLike=" + houseLike + ", deals=" + deals + "]";
	}

	@Override
	public boolean equals(Object o) {
		if(o == null || getClass() != o.getClass()) return false;
		if(this == o) return true;
		HouseDto tmp = (HouseDto) o;
		return tmp.aptCode.equals(this.aptCode);
	}
	
}
