package com.ssafy.ourhouse.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.ourhouse.domain.User;
import com.ssafy.ourhouse.dto.AptInfoDto;
import com.ssafy.ourhouse.dto.DealInfoDto;
import com.ssafy.ourhouse.dto.HouseDatabaseDto;
import com.ssafy.ourhouse.dto.HouseDto;
import com.ssafy.ourhouse.dto.UserDto;
import com.ssafy.ourhouse.dto.UserListDto;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

	@Override
	UserDto loadUserByUsername(String username) throws UsernameNotFoundException;

	void registerUser(UserDto userDto) throws Exception;

	UserDto loginUser(Map<String, String> map) throws Exception;

//	UserDto searchByUserId(String userId) throws Exception;
//	void updatePassword(Map<String, String> map) throws Exception;

	Boolean deleteUser(String email) throws Exception;

	List<UserListDto> loadAllUsers() throws Exception;

	void randomPassword(Map<String, String> map) throws Exception;

	String compareName(String userEmail) throws Exception;
	
	Map<String, Object> getUserInfo(String email) throws Exception;

	void updateUserInfo(UserDto user) throws Exception;

	List<AptInfoDto> getFavoriteHouses(String jwt) throws Exception;

	List<DealInfoDto> getFavoriteDeals(String jwt) throws Exception;

}