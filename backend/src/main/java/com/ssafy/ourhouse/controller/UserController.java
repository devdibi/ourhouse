package com.ssafy.ourhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ssafy.ourhouse.domain.Role;
import com.ssafy.ourhouse.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ourhouse.dto.UserDto;
import com.ssafy.ourhouse.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//추후 httpStatus 수정

@RestController
@RequestMapping("/user")
@Api(value = "USER 컨트롤러 API")
@RequiredArgsConstructor
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final UserService userService;
    private final JwtService jwtService;

    @ApiOperation(value = "로그인", notes = "로그인을 위해 아이디와 비밀번호 입력, 성공시 userDto 반환, 실패 시 null 값의 userDto 반환", response = Map.class)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto user) {
        logger.debug("login");
        Map<String, String> map = new HashMap<>();
        map.put("password", user.getPassword());
        map.put("email", user.getEmail());
        UserDto userDto = null;
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            userDto = userService.loginUser(map);
            String jwtToken = jwtService.generateToken(userDto);

            return new ResponseEntity<String>(jwtToken, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("로그인 실패 : {}", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>("에러 발생", HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "회원가입", notes = "회원 가입 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        int ageGroup = userDto.getAge() / 10;
        userDto.setAgeGroup(ageGroup);
        userDto.setRole(Role.USER);
        logger.debug("userDto info : {}", userDto);
        try {
            userService.registerUser(userDto);
			String jwtToken = jwtService.generateToken(userDto);

			return new ResponseEntity<String>(jwtToken, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "비밀번호 업데이트", notes = "사용자가 원하는 비밀번호로 업데이트")
    @PutMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> map) {
        logger.debug("updatePassword info : {}", map);
        try {
            userService.updatePassword(map);
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "유저 삭제", notes = "해당 이메일의 유저를 삭제, 삭제 성공 여부에 따라 success, fail 문자열 반환", response = String.class)
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable("email") @ApiParam(value = "삭제할 유저의 이메일", required = true) String userEmail) throws Exception {
        logger.debug("deleteUser " + userEmail);
        if (userService.deleteUser(userEmail)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    @ApiOperation(value = "유저 리스트", notes = "모든 유저 리스트 출력", response = List.class)
    @GetMapping("/loadAllUsers")
    public ResponseEntity<List<UserDto>> loadAllUsers() {
        logger.debug("load All Users info");
        List<UserDto> userList = null;
        try {
            userList = userService.loadAllUsers();
            logger.debug("Seccess");
            return new ResponseEntity<List<UserDto>>(userList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "비밀번호 찾기", notes = "해당 유저에게 임시 비밀번호 할당", response = String.class)
    @PutMapping("/randomPassword")
    public ResponseEntity<String> randomPassword(@RequestParam String userEmail) {
        String randomPassword = UUID.randomUUID().toString().substring(0, 8);
        logger.debug("email: '{}'", userEmail);
        logger.debug("create random password: {}", randomPassword);

        Map<String, String> map = new HashMap<String, String>();
        map.put("email", userEmail);
        map.put("password", randomPassword);

        try {
            userService.randomPassword(map);
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }

    //관심지역 출력

    //관심 거래 출력

    // 쓴 게시글 출력
}
