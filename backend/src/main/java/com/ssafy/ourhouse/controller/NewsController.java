package com.ssafy.ourhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ourhouse.dto.NewsDto;
import com.ssafy.ourhouse.service.NewsService;
import com.ssafy.ourhouse.util.NaverSearch;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 600)
@RestController
@RequestMapping("/news")
@Api(value = "News 컨트롤러 API")
public class NewsController {
	
	private final String SUCCESS = "success";
	
	private final String FAIL = "fail";
	
	private final Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	@Autowired
	private NewsService newsService;

	public NewsController(NewsService newsService) {
		super();
		this.newsService = newsService;
	}
	
	// list
	@ApiOperation(value = "모든 News 반환")
	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getNews(){
		logger.info("NewsList 모두 반환");
		List<NewsDto> newsList;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			newsList = newsService.getNews();
			resultMap.put("message", "success");
			resultMap.put("newsList", newsList);
			return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
		}catch(Exception e) {
			resultMap.put("message", "fail");
			return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.NO_CONTENT);
		}
		 
	}
	
	// hit 추가
	@ApiOperation(value="뉴스 조회수 추가")
	@PutMapping("/{newsNo}")
	public ResponseEntity<String> updateBoard(@PathVariable("newsNo")int newsNo){
		logger.info("뉴스 조회수 추가");
		try {
			newsService.updateHit(newsNo);
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
		}
	}
	
	// Spring Boot Scheduler(뉴스 리스트 자동 갱신) - 매일 9시 수
	// 일단 뉴스를 json으로 가져와서 바로 DB로 적용하는 방식으로 진행 뉴스 기사는 1회 100개 수행
	// Scheduler와 버튼으로 관리자에서 구현하는 방식으로 수
	@ApiOperation(value="뉴스 추가 버튼으로 구현(겹치는 뉴스기사를 불러올 수도 있습니다.)")
	@PostMapping("/")
	public ResponseEntity<String> updateNews(){
		logger.info("뉴스 추가");
		JSONObject news = null;
		NewsDto newsDto = null;
		try {
			JSONArray json = NaverSearch.jsonSave();
			for(int i = 0; i < json.length(); i++) {
				newsDto = new NewsDto();
				news = json.getJSONObject(i);
				newsDto.setTitle(news.get("title").toString());
				newsDto.setContext(news.get("description").toString());
				newsDto.setLink(news.get("link").toString());
				newsDto.setPostDate(news.get("pDate").toString());
				newsService.updateNews(newsDto);
			}
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
		}
	}
}