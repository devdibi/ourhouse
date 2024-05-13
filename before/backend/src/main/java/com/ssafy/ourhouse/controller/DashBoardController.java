package com.ssafy.ourhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ourhouse.service.DashBoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge=600)
@RestController
@RequestMapping("/dashboard")
@Api(value="dashboard 컨트롤러 API")
public class DashBoardController {

	private final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private DashBoardService dashBoardService;
	
	public DashBoardController(DashBoardService dashBoardService) {
		super();
		this.dashBoardService = dashBoardService;
	}


	@ApiOperation(value="연도별 평균 거래량")
	@GetMapping("/{dongCode}")
	public ResponseEntity<Map<String, Object>> getLists(@PathVariable long dongCode){
		logger.info("연도별 평균 거래랑 반환");
		List<Integer> averageList;
		List<Integer> yearAmountList;
		List<Integer> monthAmountList;
		List<Object> geoList;
		List<Object> ageList;
		List<Object> genderList;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			averageList = dashBoardService.getAverages(dongCode);
			yearAmountList = dashBoardService.getYearAmount(dongCode);
			monthAmountList = dashBoardService.getMonthAmount(dongCode);
			geoList = dashBoardService.getPolygon(dongCode);
			ageList = dashBoardService.getAge(dongCode);
			genderList = dashBoardService.getGender(dongCode);
			resultMap.put("message", "success");
			resultMap.put("averageList", averageList);
			resultMap.put("yearAmountList", yearAmountList);
			resultMap.put("monthAmountList", monthAmountList);
			resultMap.put("geoList", geoList);
			resultMap.put("ageList", ageList);
			resultMap.put("genderList", genderList);
			return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
		}catch(Exception e) {
			resultMap.put("message", "fail");
			return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.NO_CONTENT);
		}
		 
	}
	
	
}
