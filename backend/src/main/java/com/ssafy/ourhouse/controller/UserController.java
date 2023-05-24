package com.ssafy.ourhouse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ssafy.ourhouse.domain.Role;
import com.ssafy.ourhouse.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ourhouse.dto.AptInfoDto;
import com.ssafy.ourhouse.dto.DealInfoDto;
import com.ssafy.ourhouse.dto.HouseDatabaseDto;
import com.ssafy.ourhouse.dto.HouseDto;
import com.ssafy.ourhouse.dto.UserDto;
import com.ssafy.ourhouse.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//추후 httpStatus 수정
@CrossOrigin(origins = { "*" }, maxAge = 600)
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
    private final AuthenticationManager authenticationManager;

    // TODO: Logic 코드를 나중에 서비스 계층으로 빼기
    @PostMapping(value = "/authenticate", produces = "application/json; charset=utf8")
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody UserDto user) {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(), user.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            logger.warn("'{}'님의 로그인 실패: {}", user.getEmail(), e.toString());
            String errorMsg = "로그인에 실패했습니다.";
            resultMap.put("message", FAIL);
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.UNAUTHORIZED);
        }

        UserDto findUser = userService.loadUserByUsername(user.getEmail());
        String jwtToken = jwtService.generateToken(findUser);
        resultMap.put("userRole", findUser.getRole());
        resultMap.put("jwtToken", jwtToken);
        resultMap.put("message", SUCCESS);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

//    // TODO: JWT 사용할 예정이기에, 나중에 제거하기!
//    @ApiOperation(value = "로그인", notes = "로그인을 위해 아이디와 비밀번호 입력, 성공시 userDto 반환, 실패 시 null 값의 userDto 반환", response = Map.class)
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody UserDto user) {
//        logger.debug("login");
//        Map<String, String> map = new HashMap<>();
//        map.put("password", user.getPassword());
//        map.put("email", user.getEmail());
//        UserDto userDto = null;
//        HttpStatus status = HttpStatus.ACCEPTED;
//        try {
//            userDto = userService.loginUser(map);
//            String jwtToken = jwtService.generateToken(userDto);
//
//            return new ResponseEntity<String>(jwtToken, HttpStatus.OK);
//        } catch (Exception e) {
//            logger.error("로그인 실패 : {}", e);
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<String>("에러 발생", HttpStatus.NO_CONTENT);
//    }

    @ApiOperation(value = "회원가입", notes = "회원 가입 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        int ageGroup = userDto.getAge() / 10;
        userDto.setAgeGroup(ageGroup);
        userDto.setRole(Role.USER);
        logger.debug("userDto info : {}", userDto);
        try {
            userService.registerUser(userDto);
//            String jwtToken = jwtService.generateToken(userDto);
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
        }
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

//	@ApiOperation(value = "비밀번호 업데이트", notes="사용자가 원하는 비밀번호로 업데이트")
//	@PutMapping("/updatePassword")
//	public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> map) {
//		logger.debug("updatePassword info : {}", map);
//		try {
//			userService.updatePassword(map);
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
//		}
//	}
	
	@ApiOperation(value="유저 삭제", notes="해당 이메일의 유저를 삭제, 삭제 성공 여부에 따라 success, fail 문자열 반환", response=String.class)
	@DeleteMapping("/{email}")
	public ResponseEntity<String> deleteUser (@PathVariable("email")  @ApiParam(value = "삭제할 유저의 이메일", required = true)String userEmail) throws Exception{
		logger.debug("deleteUser " + userEmail);
		if(userService.deleteUser(userEmail)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
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
				logger.debug("존재하지 않음");
			}else {
				logger.debug("존재함");
				resultMap.put("message", FAIL);				
			}
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);			
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("message", FAIL);
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		}
	}

	
	@ApiOperation(value = "마이페이지 출력을 위한 유저 정보 반환", notes="성공/실패 메세지와 함께 현재 로그인한 사용자 정보 반환")
	@GetMapping("/getUserInfo")
	public ResponseEntity<Map<String, Object>> getUserInfo(@RequestHeader("Authorization") String jwt){
		System.out.println("jwt: " + jwt);
		String userEmail = jwtService.extractUserEmail(jwt.replace("Bearer ", ""));
		logger.debug("유저 정보 반환, email : {}", userEmail);
		Map<String, Object> resultMap = null;
		try {
			resultMap = userService.getUserInfo(userEmail);
			resultMap.put("message", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("message", FAIL);
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "유저 정보 수정", notes="성공/실패 메세지 반환")
	@PutMapping("/updateUserInfo")
	public ResponseEntity<Map<String, Object>> updateUserInfo(@RequestHeader("Authorization") String jwt, @RequestBody UserDto user){
		logger.debug("회원 정보 수정, email : {}", user.getEmail());
		System.out.println(user);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			userService.updateUserInfo(user);
			resultMap.put("message", SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", FAIL);
		}
		System.out.println(user);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저의 관심 아파트 단지 출력", notes="유저의 관심 단지 리스트로 출력")
	@GetMapping("/getFavoriteHouses")
	public ResponseEntity<Map<String, Object>> getFavoriteHouses(@RequestHeader("Authorization") String jwt){
		logger.debug("관심 단지 출력");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<AptInfoDto> houses = null;
		try {
			houses = userService.getFavoriteHouses(jwt);
			resultMap.put("message", SUCCESS);
			resultMap.put("list", houses);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("list", houses);
			resultMap.put("message", FAIL);
		}
		System.out.println(houses);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "유저의 관심 거래 출력", notes="유저의 관심 거래를 리스트로 출력")
	@GetMapping("/getFavoriteDeals")
	public ResponseEntity<Map<String, Object>> getFavoriteDeals(@RequestHeader("Authorization") String jwt){
		logger.debug("관심 거래 출력");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<DealInfoDto> houses = null;
		try {
			houses = userService.getFavoriteDeals(jwt);
			resultMap.put("message", SUCCESS);
			resultMap.put("list", houses);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("list", houses);
			resultMap.put("message", FAIL);
		}
		System.out.println(houses);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
}
