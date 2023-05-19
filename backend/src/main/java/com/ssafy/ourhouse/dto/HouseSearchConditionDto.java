package com.ssafy.ourhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "아파트 검색 조건을 저장하는 Dto")
public class HouseSearchConditionDto {

	@ApiModelProperty(value = "검색할 아파트의 동 코드")
	private String dongCode;

	@ApiModelProperty(value = "검색하는 사람의 아이디(좋아요 정보 처리)")
	private String email;

	@ApiModelProperty(value = "아파트의 거래 년도")
	private int year;

	@ApiModelProperty(value = "아파트의 거래 월")
	private int month;

	@ApiModelProperty(value = "아파트의 가격(최소값)")
	private int minPrice;
	
	@ApiModelProperty(value = "아파트의 가격(최댓값)")
	private int maxPrice;
	
}
