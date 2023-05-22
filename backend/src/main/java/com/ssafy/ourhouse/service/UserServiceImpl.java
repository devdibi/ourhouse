package com.ssafy.ourhouse.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ssafy.ourhouse.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.ssafy.ourhouse.dto.UserDto;
import com.ssafy.ourhouse.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;
	private MailSender mailSender;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserMapper userMapper, MailSender mailSender, PasswordEncoder passwordEncoder) {
		super();
		this.userMapper = userMapper;
		this.mailSender = mailSender;
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public UserServiceImpl(UserMapper userMapper, @Lazy PasswordEncoder passwordEncoder) {
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDto loadUserByUsername(String username) {

		try {
			return userMapper.findUserByEmail(username);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void registerUser(UserDto userDto) throws Exception {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userMapper.registerUser(userDto);
	}

	@Override
	public UserDto loginUser(Map<String, String> map) throws Exception {
		return userMapper.loginUser(map);
	}

	@Override
	public void randomPassword(Map<String, String> map) throws Exception {
		// 랜덤 패스워드 생성
		String randomPassword = UUID.randomUUID().toString().substring(0, 8);
		map.put("password", randomPassword);
		userMapper.randomPassword(map);
		// 이메일로 전송
		// 이메일 세팅
		SimpleMailMessage smm = new SimpleMailMessage();
		MailSender sender;
		smm.setFrom("ourhouseproject2023@gmail.com");
		smm.setTo(map.get("email"));
		smm.setSubject("새 비밀번호가 전송되었습니다.");
		smm.setText("우리의 집 페이지\n새로운 비밀번호입니다.\n비밀번호:" + randomPassword);
		mailSender.send(smm);
		System.out.println("이메일 전송 완료");
	}

	@Override
	public String compareName(String userEmail) throws Exception {
		return userMapper.compareName(userEmail);
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
}
