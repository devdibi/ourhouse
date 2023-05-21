package com.ssafy.ourhouse.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.ourhouse.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

	@Override
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	void registerUser(UserDto userDto) throws Exception;

    UserDto loginUser(Map<String, String> map) throws Exception;

    //	UserDto searchByUserId(String userId) throws Exception;
    void updatePassword(Map<String, String> map) throws Exception;

    Boolean deleteUser(String email) throws Exception;

    List<UserDto> loadAllUsers() throws Exception;

    void randomPassword(Map<String, String> map) throws Exception;
}
