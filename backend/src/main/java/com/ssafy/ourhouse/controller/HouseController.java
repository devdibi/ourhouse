package com.ssafy.ourhouse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ourhouse.dto.DongDto;
import com.ssafy.ourhouse.dto.HouseDto;
import com.ssafy.ourhouse.dto.HouseSearchConditionDto;
import com.ssafy.ourhouse.dto.LikeDto;
import com.ssafy.ourhouse.dto.SidoDto;
import com.ssafy.ourhouse.dto.SigunguDto;
import com.ssafy.ourhouse.service.HouseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, maxAge = 600)
@RestController
@RequestMapping("/house")
@Api(value = "HOUSE 컨트롤러 API")
public class HouseController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private HouseService houseService;

	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.")
	@GetMapping("/sido")
	public ResponseEntity<Map<String, Object>> getSido() {
		logger.info("시도 반환");
		List<SidoDto> sidoList;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			sidoList = houseService.getSido();
			resultMap.put("message", "success");
			resultMap.put("sidoList", sidoList);
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "시군구 반환", notes = "해당 시도의 시군구 정보를 반환한다.")
	@GetMapping("/sigungu")
	public ResponseEntity<Map<String, Object>> getSigungu(@RequestParam("sido") @ApiParam(value = "시도 코드") String sido) {
		logger.info("시군구 반환");
		List<SigunguDto> sigunguList;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			sigunguList = houseService.getSigungu(sido);
			resultMap.put("message", "success");
			resultMap.put("sidoList", sigunguList);
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "동코드 반환", notes = "해당 시군구의 동 정보를 반환한다.")
	@GetMapping("/dong")
	public ResponseEntity<Map<String, Object>> getDong(@ApiParam(value = "시군구 코드") @RequestParam String sigungu) {
		logger.info("동코드 반환");
		List<DongDto> dongList;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			dongList = houseService.getDong(sigungu);
			resultMap.put("message", "success");
			resultMap.put("dongList", dongList);
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "조건을 충족하는 거래와 유저의 좋아요 여부 출력")
	@PostMapping("/")
	public ResponseEntity<Map<String, Object>> houseSearch(@RequestBody HouseSearchConditionDto searchCondition) {
		System.out.println("house 출력");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<HouseDto> houseList = new ArrayList<HouseDto>();
		try {
			houseList = houseService.houseSearch(searchCondition);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		}
		resultMap.put("data", houseList);
		resultMap.put("message", "success");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "아파트 좋아요 설정", notes = "유저가 누른 아파트의 좋아요 설정")
	@ApiImplicitParam()
	@PostMapping("/like_house")
	public ResponseEntity<Map<String, Object>> houseLike(@RequestBody LikeDto likeDto) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			houseService.houseLike(likeDto.getEmail(), likeDto.getCode());
			resultMap.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "거래 좋아요 설정", notes = "유저가 누른 거래의 좋아요 설정")
	@PostMapping("/like_deal")
	public ResponseEntity<Map<String, Object>> dealLike(@RequestBody LikeDto likeDto) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			houseService.dealLike(likeDto.getEmail(), likeDto.getCode());
			resultMap.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			resultMap.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "아파트 좋아요 삭제", notes = "유저가 누른 아파트의 좋아요 삭제")
	@DeleteMapping("/like_house")
	public ResponseEntity<Map<String, Object>> houseDislike(@RequestBody LikeDto likeDto) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			houseService.houseDislike(likeDto.getEmail(), likeDto.getCode());
			resultMap.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "거래 좋아요 삭제", notes="유저가 누른 거래의 좋아요 삭제")
	@DeleteMapping("/like_deal")
	public ResponseEntity<Map<String, Object>> dealDislike (@RequestBody LikeDto likeDto){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			houseService.dealDislike(likeDto.getEmail(), likeDto.getCode());
			resultMap.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
		}
	}
	
}
