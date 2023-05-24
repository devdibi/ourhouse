package com.ssafy.ourhouse.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "아파트 정보 출력", description = "주소, 평균 가격과 면적, 아파트 이름, 좋아요 수, 동코드")
public class AptInfoDto {
	
	private String sidoName;
	
	private String gugunName;
	
	private String dongName;
	
	private String jibunAddress;
	
	private String aptName;
	
	private String priceAvg;
	
	private String areaAvg;
	
	private int likeNums;
	
	private String dongCode;

	@Override
	public String toString() {
		return "AptInfoDto [sidoName=" + sidoName + ", gugunName=" + gugunName + ", dongName=" + dongName
				+ ", jibunAddress=" + jibunAddress + ", aptName=" + aptName + ", priceAvg=" + priceAvg + ", areaAvg="
				+ areaAvg + ", likeNums=" + likeNums + ", dongCode=" + dongCode + "]";
	}
	
}
