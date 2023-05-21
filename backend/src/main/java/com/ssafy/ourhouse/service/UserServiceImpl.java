package com.ssafy.ourhouse.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.ourhouse.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ssafy.ourhouse.dto.UserDto;
import com.ssafy.ourhouse.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        super();
        this.userMapper = userMapper;
    }

    @Override
    public User loadUserByUsername(String username) {

        try {
            return userMapper.findUserByEmail(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registerUser(UserDto userDto) throws Exception {
        userMapper.registerUser(userDto);
    }

    @Override
    public UserDto loginUser(Map<String, String> map) throws Exception {
        return userMapper.loginUser(map);
    }

    @Override
    public void updatePassword(Map<String, String> map) throws Exception {
        userMapper.updatePassword(map);
    }

    @Override
    public Boolean deleteUser(String email) throws Exception {
        return userMapper.deleteUser(email);
    }

    @Override
    public List<UserDto> loadAllUsers() throws Exception {
        return userMapper.loadAllUsers();
    }

    @Override
    public void randomPassword(Map<String, String> map) throws Exception {
        userMapper.randomPassword(map);
    }
}
