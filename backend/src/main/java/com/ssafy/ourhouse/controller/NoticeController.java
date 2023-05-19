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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ourhouse.dto.NoticeDto;
import com.ssafy.ourhouse.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge=600)
@RestController
@RequestMapping("/notice")
@Api(value="Notice 컨트롤러 API")
public class NoticeController {
	
	private final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NoticeService noticeService;
	
	public NoticeController(NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}

	// list
	@ApiOperation(value = "모든 Notice 반환")
	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getNotices(){
		logger.info("NoticeList 모두 반환");
		List<NoticeDto> noticeList;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			noticeList = noticeService.getNotices();
			resultMap.put("message", "success");
			resultMap.put("noticeList", noticeList);
			return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
		}catch(Exception e) {
			resultMap.put("message", "fail");
			return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.NO_CONTENT);
		}
		 
	}
	
	// detail, hit, comment
	@ApiOperation(value = "게시글의 상세페이지 조회 및 댓글 조")
	@GetMapping("/{noticeNo}")
	public ResponseEntity<Map<String,Object>> getNotice(@PathVariable("noticeNo")int noticeNo ){
		logger.info("게시글을 불러와요");
		NoticeDto notice;
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			noticeService.updateHit(noticeNo);
			notice = noticeService.getNotice(noticeNo);
			resultMap.put("message", SUCCESS);
			resultMap.put("notice", notice);
			return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			resultMap.put("message", FAIL);
			return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.NO_CONTENT);
		}
		
	}
	
	// update
	@ApiOperation(value="게시글을 수정해 보아요")
	@PutMapping("/{noticeNo}")
	public ResponseEntity<String> updateNotice(@PathVariable("noticeNo")int noticeNo, @RequestBody NoticeDto noticeDto){
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
	@ApiOperation(value="공지사항에 글을 작성해보아요")
	@PostMapping("/")
	public ResponseEntity<String> writeNotice(@RequestBody NoticeDto noticeDto){
		logger.debug("noticeDto info: {}", noticeDto);
		try {
			noticeService.writeNotice(noticeDto);
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="게시글을 삭제해 보아요")
	@DeleteMapping("/{noticeNo}")
	public ResponseEntity<String> deleteNotice(@PathVariable("noticeNo") int noticeNo){
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
