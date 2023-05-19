package com.ssafy.ourhouse.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ourhouse.dto.UserDto;

@Mapper
public interface UserMapper {
	
	void registerUser(UserDto userDto) throws SQLException;
	
	UserDto loginUser(Map<String, String> map) throws SQLException;
	
	void updatePassword(Map<String, String> map) throws SQLException;
	
	Boolean deleteUser(String email) throws SQLException;
	
	List<UserDto> loadAllUsers() throws SQLException;
	
	void randomPassword(Map<String, String> map) throws SQLException;

}
