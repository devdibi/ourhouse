package com.ssafy.ourhouse.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ourhouse.api.BusAPI;
import com.ssafy.ourhouse.dto.BusStopDto;
import com.ssafy.ourhouse.dto.CommercialDto;
import com.ssafy.ourhouse.dto.DongDto;
import com.ssafy.ourhouse.dto.HouseDto;
import com.ssafy.ourhouse.dto.HouseSearchConditionDto;
import com.ssafy.ourhouse.dto.LikeDto;
import com.ssafy.ourhouse.dto.SidoDto;
import com.ssafy.ourhouse.dto.SigunguDto;
import com.ssafy.ourhouse.service.HouseService;
import com.ssafy.ourhouse.service.JwtService;

import io.jsonwebtoken.io.IOException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = { "*" }, maxAge = 600)
@RestController
@RequestMapping("/house")
@RequiredArgsConstructor
@Api(value = "HOUSE 컨트롤러 API")
public class HouseController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	private final BusAPI busAPI;

	private final HouseService houseService;
//	private final JwtService JwtService;

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
	public ResponseEntity<Map<String, Object>> getSigungu(
			@RequestParam("sido") @ApiParam(value = "시도 코드") String sido) {
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
	public ResponseEntity<Map<String, Object>> houseLike(@RequestBody Long apartmentCode,
			@RequestHeader("Authorization") String jwt) {

		logger.info("jwt: {}", jwt);
		logger.info("apartmentCode: {}", apartmentCode);

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			houseService.houseLike(apartmentCode, jwt);
			resultMap.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "아파트 좋아요 삭제", notes = "유저가 누른 아파트의 좋아요 삭제")
	@DeleteMapping("/dislike_house")
	public ResponseEntity<Map<String, Object>> houseDisLike(@RequestBody Map<String, Long> data,
			@RequestHeader("Authorization") String jwt) {

		logger.info("jwt: {}", jwt);
		logger.info("apartmentCode: {}", data.get("apartmentCode"));
		Long apartmentCode = data.get("apartmentCode");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			houseService.houseDislike(apartmentCode, jwt);
			resultMap.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
		}

//		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "거래 좋아요 설정", notes = "유저가 누른 거래의 좋아요 설정")
	@PostMapping("/like_deal")
	public ResponseEntity<Map<String, Object>> dealLike(@RequestBody Long apartmentCode,
			@RequestHeader("Authorization") String jwt) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			houseService.dealLike(apartmentCode, jwt);
			resultMap.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			resultMap.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "거래 좋아요 삭제", notes = "유저가 누른 거래의 좋아요 삭제")
	@DeleteMapping("/like_deal")
	public ResponseEntity<Map<String, Object>> dealDislike(@RequestBody Map<String, Long> data,
			@RequestHeader("Authorization") String jwt) {

		logger.info("jwt: {}", jwt);
		logger.info("apartmentCode: {}", data.get("apartmentCode"));

		Long apartmentCode = data.get("apartmentCode");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			houseService.dealDislike(apartmentCode, jwt);
			resultMap.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(value = "/bus", produces = "application/json; charset=utf8")
	public String getBusStations(@RequestBody Map<String, String> data) throws Exception {
//		System.out.println("=======================");
//		System.out.println(data.get("lat"));
//		System.out.println(data.get("lng"));
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1613000/BusSttnInfoInqireService/getCrdntPrxmtSttnList"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=jxxl3rLm%2FskTLPONvQj6KXkjSZlpHSCuH8JPt9ueN49v0P0hU68Wew6dcM2ihtRP%2BQsUHpOGexuUVwl9XyOwBw%3D%3D"); /*
																														 * Service
																														 * Key
																														 */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("50", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "="
				+ URLEncoder.encode("json", "UTF-8")); /* 데이터 타입(xml, json) */
		urlBuilder.append("&" + URLEncoder.encode("gpsLati", "UTF-8") + "="
				+ URLEncoder.encode(data.get("lat"), "UTF-8")); /* WGS84 위도 좌표 */
		urlBuilder.append("&" + URLEncoder.encode("gpsLong", "UTF-8") + "="
				+ URLEncoder.encode(data.get("lng"), "UTF-8")); /* WGS84 경도 좌표 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line).append("\n");
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		return sb.toString();
	}

	@GetMapping(value = "/commercial", produces = "application/json; charset=utf8")
	public List<CommercialDto> getCommercial(@RequestParam double lat, @RequestParam double lng) throws Exception {
		System.out.println(lat);
		System.out.println(lng);
		return houseService.getNearByCommercial(lat, lng);
	}

	@GetMapping(value = "/cctv", produces = "application/json; charset=utf8")
	public String getCCTV() throws Exception {
		StringBuilder urlBuilder = new StringBuilder("https://openapi.its.go.kr:9443/cctvInfo"); /* URL */
		urlBuilder.append(
				"?" + URLEncoder.encode("apiKey", "UTF-8") + "=" + URLEncoder.encode("78816c1619534c18b1f6b436fa4ad772", "UTF-8")); /* 공개키 */
		urlBuilder
				.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("all", "UTF-8")); /* 도로유형 */
		urlBuilder.append(
				"&" + URLEncoder.encode("cctvType", "UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); /* CCTV유형 */
		urlBuilder.append(
				"&" + URLEncoder.encode("minX", "UTF-8") + "=" + URLEncoder.encode("128.58096040982386", "UTF-8")); /* 최소경도영역 */
		urlBuilder.append(
				"&" + URLEncoder.encode("maxX", "UTF-8") + "=" + URLEncoder.encode("128.60606031174967", "UTF-8")); /* 최대경도영역 */
		urlBuilder.append(
				"&" + URLEncoder.encode("minY", "UTF-8") + "=" + URLEncoder.encode("35.87656409728413", "UTF-8")); /* 최소위도영역 */
		urlBuilder.append(
				"&" + URLEncoder.encode("maxY", "UTF-8") + "=" + URLEncoder.encode("35.858406471457855", "UTF-8")); /* 최대위도영역 */
		urlBuilder.append(
				"&" + URLEncoder.encode("getType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /* 출력타입 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "text/xml;charset=UTF-8");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
//		System.out.println(sb.toString());
		return sb.toString();
	}
}
