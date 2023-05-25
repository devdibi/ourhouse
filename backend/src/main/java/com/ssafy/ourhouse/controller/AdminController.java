package com.ssafy.ourhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ourhouse.dto.NoticeDto;
import com.ssafy.ourhouse.dto.UserDto;
import com.ssafy.ourhouse.dto.UserListDto;
import com.ssafy.ourhouse.service.JwtService;
import com.ssafy.ourhouse.service.NoticeService;
import com.ssafy.ourhouse.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@Api(value = "관리자 컨트롤러 API")
@RequiredArgsConstructor
public class AdminController {
	
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final UserService userService;
    private final JwtService jwtService;
    private final NoticeService noticeService;

    @GetMapping
    public String get() {
        return "GET Method in Admin Controller";
    }
    
	@ApiOperation(value = "유저 리스트", notes = "모든 유저 리스트 출력", response = List.class)
	@GetMapping("/loadAllUsers")
	public ResponseEntity<Map<String, Object>> loadAllUsers(@RequestHeader("Authorization") String jwt) {
		logger.debug("load All Users info");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<UserListDto> userList = userService.loadAllUsers();
			logger.debug("Seccess");
			resultMap.put("userInfo", userList);
			resultMap.put("message", SUCCESS);			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", FAIL);			
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value="유저 삭제", notes="해당 이메일의 유저를 삭제, 삭제 성공 여부에 따라 success, fail 문자열 반환", response=String.class)
	@DeleteMapping("/{email}")
	public ResponseEntity<String> deleteUser (@PathVariable("email")  @ApiParam(value = "삭제할 유저의 이메일", required = true)String userEmail, @RequestHeader("Authorization") String jwt) throws Exception{
		logger.debug("deleteUser " + userEmail);
		if(userService.deleteUser(userEmail)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	// update
	@ApiOperation(value="공지를 수정해 보아요")
	@PutMapping("/{noticeNo}")
	public ResponseEntity<String> updateNotice(@PathVariable("noticeNo")int noticeNo, @RequestBody NoticeDto noticeDto, @RequestHeader("Authorization") String jwt){
		logger.info("게시글을 수정해봅시다잉");
		try {
			noticeService.updateNotice(noticeNo, noticeDto);
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
		}
	}

	// write
	@ApiOperation(value="공지에 글을 작성해보아요")
	@PostMapping("/")
	public ResponseEntity<String> writeNotice(@RequestBody NoticeDto noticeDto, @RequestHeader("Authorization") String jwt){
		logger.debug("noticeDto info: {}", noticeDto);
		try {
			noticeService.writeNotice(noticeDto);
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="공지를 삭제해 보아요")
	@DeleteMapping("/{noticeNo}")
	public ResponseEntity<String> deleteNotice(@PathVariable("noticeNo") int noticeNo, @RequestHeader("Authorization") String jwt){
		logger.info("게시글 삭제할게요");
		try {
			noticeService.deleteNotice(noticeNo);
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
		}
	}
}
