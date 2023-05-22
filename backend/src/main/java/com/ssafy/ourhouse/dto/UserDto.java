package com.ssafy.ourhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "UserDto : 사용자에 대한 Dto ", description = "사용자 정보를 알려주는 Dto")
public class UserDto {

	@ApiModelProperty(value = "이메일, pk")
	private String email;

	@ApiModelProperty(value = "비밀번호")
	private String password;

	@ApiModelProperty(value = "사용자 이름")
	private String name;

	@ApiModelProperty(value = "성별. 1:남자, 2:여자, 0:관리자")
	private int gender;

	@ApiModelProperty(value = "나이")
	private int age;

	@ApiModelProperty(value = "나잇대 (나이/10)")
	private int ageGroup;
	
	@ApiModelProperty(value = "거주지역 동코드")
	private String dwellArea;

	@ApiModelProperty(value = "관심지역 동코드")
	private String favoriteArea;

	@ApiModelProperty(value = "상태. 0:비활성, 1:활성")
	private int status;

	@Override
	public String toString() {
		return "UserDto [email=" + email + ", password=" + password + ", name=" + name + ", gender=" + gender + ", age="
				+ age + ", ageGroup=" + ageGroup + ", dwellArea=" + dwellArea + ", favoriteArea=" + favoriteArea
				+ ", status=" + status + "]";
	}
}
