package com.ssafy.ourhouse.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserListDto {

    @ApiModelProperty(value = "이메일, pk")
    private String email;

    @ApiModelProperty(value = "사용자 이름")
    private String name;

    @ApiModelProperty(value = "성별. 1:남자, 2:여자, 0:관리자")
    private int gender;

    @ApiModelProperty(value = "나이")
    private int age;

	@Override
	public String toString() {
		return "UserListDto [email=" + email + ", name=" + name + ", gender=" + gender + ", age=" + age + "]";
	}
	
}
