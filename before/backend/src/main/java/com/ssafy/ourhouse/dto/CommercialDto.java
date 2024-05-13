package com.ssafy.ourhouse.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommercialDto {
	
	private String name;

	private String category;
	
	private String classification;
	
	private String category_name;
	
	private String dong_code;
	
	private String dong_name;
	
	private String jibun_address;
	
	private String road_address;
	
	private double lng;
	
	private double lat;
}
