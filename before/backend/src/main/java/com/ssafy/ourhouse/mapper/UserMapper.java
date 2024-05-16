package com.ssafy.ourhouse.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.ourhouse.domain.User;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ourhouse.dto.AptInfoDto;
import com.ssafy.ourhouse.dto.DealInfoDto;
import com.ssafy.ourhouse.dto.HouseDatabaseDto;
import com.ssafy.ourhouse.dto.HouseDto;
import com.ssafy.ourhouse.dto.UserDto;
import com.ssafy.ourhouse.dto.UserListDto;

@Mapper
public interface UserMapper {

	UserDto findUserByEmail(String email) throws SQLException;

    void registerUser(UserDto userDto) throws SQLException;

    UserDto loginUser(Map<String, String> map) throws SQLException;

//    void updatePassword(Map<String, String> map) throws SQLException;

    Boolean deleteUser(String email) throws SQLException;

    List<UserListDto> loadAllUsers() throws SQLException;

    void randomPassword(Map<String, String> map) throws SQLException;

	String compareName(String userEmail) throws SQLException;

	UserDto getUserInfo(String email) throws SQLException;

	String getSidoName(String substring) throws SQLException;
	
	String getSigunguName(String substring) throws SQLException;
	
	String getDongName(String substring) throws SQLException;

	void updateUserInfo(UserDto user) throws SQLException;

	List<AptInfoDto> getFavoriteHouses(String email) throws SQLException;

	List<DealInfoDto> getFavoriteDeals(String email) throws SQLException;

}