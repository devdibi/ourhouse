package com.ssafy.ourhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = { "*" }, maxAge = 600)
@RestController
@RequestMapping("/user")
@Api(value="USER 컨트롤러 API")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private UserService userService;

	@ApiOperation(value = "로그인", notes = "로그인을 위해 아이디와 비밀번호 입력, 성공시 userDto 반환, 실패 시 null 값의 userDto 반환", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody UserDto user){
		logger.debug("login");
		Map<String, String> map = new HashMap<>();
		map.put("password", user.getPassword());
		map.put("email", user.getEmail());
		UserDto userDto = null;
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userDto = userService.loginUser(map);
			return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<UserDto>(userDto, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "회원가입", notes = "회원 가입 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserDto userDto) {
		int ageGroup = userDto.getAge() / 10;
		userDto.setAgeGroup(ageGroup);
		logger.debug("userDto info : {}", userDto);
		try {
			userService.registerUser(userDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "비밀번호 업데이트", notes="사용자가 원하는 비밀번호로 업데이트")
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
	
	@ApiOperation(value="유저 삭제", notes="해당 이메일의 유저를 삭제, 삭제 성공 여부에 따라 success, fail 문자열 반환", response=String.class)
	@DeleteMapping("/{email}")
	public ResponseEntity<String> deleteUser (@PathVariable("email")  @ApiParam(value = "삭제할 유저의 이메일", required = true)String userEmail) throws Exception{
		logger.debug("deleteUser " + userEmail);
		if(userService.deleteUser(userEmail)) {
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
	
	@ApiOperation(value = "비밀번호 찾기", notes = "해당 유저 정보를 비교 후, 이메일과 이름이 일치할 시 임시 비밀번호 이메일을 통해 할당", response = String.class)
	@GetMapping("/randomPassword")
	public ResponseEntity<Map<String, Object>> randomPassword(@RequestParam String userEmail, @RequestParam String userName){
		logger.debug("유저 이메일({})과 이름({}) 비교", userEmail, userName);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String name = userService.compareName(userEmail);
			//이름과 이메일이 일치할 때
			if(userName.equals(name)) {
				logger.debug("일치");
				resultMap.put("compareResult", SUCCESS);
				
				//비밀번호 재발급 후 이메일 전송(서비스에서)
				Map<String, String> map = new HashMap<String, String>();
				map.put("email", userEmail);
				userService.randomPassword(map);
				logger.debug("이메일 전송 완료");
				resultMap.put("message", SUCCESS);
				return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
			}
			//불일치할 때
			else {
				logger.debug("불일치");
				resultMap.put("compareResult", FAIL);
				resultMap.put("message", SUCCESS);
				return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", FAIL);
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="이메일 중복 체크", notes="해당 이메일과 동일한 이메일이 있을 시 fail/ 없을 시 success 반환")
	@GetMapping("/emailCheck")
	public ResponseEntity<Map<String, Object>> emailCheck(@RequestParam String userEmail){
		logger.debug("유저 이메일({})이 존재하는지 확인", userEmail);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String name = userService.compareName(userEmail);
			System.out.println(name);
			if(name==null || name.equals("")) {
				resultMap.put("message", SUCCESS);
			}else {
				resultMap.put("message", FAIL);				
			}
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);			
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("message", FAIL);
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		}
	}

}
