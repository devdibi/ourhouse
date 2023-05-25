package com.ssafy.ourhouse.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "거래 정보 출력", description = "주소, 가격과 면적, 아파트 이름, 좋아요 수, 동코드")
public class DealInfoDto {

	private String sidoName;
	
	private String gugunName;
	
	private String dongName;
	
	private String jibunAddress;
	
	private String aptName;
	
	private String price;
	
	private String area;
	
	private int floor;
	
	private int likeNums;
	
	private String dongCode;
	
}
