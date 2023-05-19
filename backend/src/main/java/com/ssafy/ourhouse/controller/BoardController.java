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

import com.ssafy.ourhouse.dto.BoardDto;
import com.ssafy.ourhouse.dto.CommentDto;
import com.ssafy.ourhouse.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge=600)
@RestController
@RequestMapping("/board")
@Api(value="Board 컨트롤러 API")
public class BoardController {
	
	private final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private BoardService boardService;
	
	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}

	// list
	@ApiOperation(value = "모든 Board 반환")
	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getBoards(){
		logger.info("BoardList 모두 반환");
		List<BoardDto> boardList;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			boardList = boardService.getBoards();
			resultMap.put("message", "success");
			resultMap.put("boardList", boardList);
			return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
		}catch(Exception e) {
			resultMap.put("message", "fail");
			return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.NO_CONTENT);
		}
		 
	}
	
	// detail, hit, comment
	@ApiOperation(value = "게시글의 상세페이지 조회 및 댓글 조")
	@GetMapping("/{boardNo}")
	public ResponseEntity<Map<String,Object>> getBoard(@PathVariable("boardNo")int boardNo ){
		logger.info("게시글을 불러와요");
		BoardDto board;
		List<CommentDto> commentList;
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			boardService.updateHit(boardNo);
			board = boardService.getBoard(boardNo);
			commentList = boardService.getComment(boardNo);
			resultMap.put("message", SUCCESS);
			resultMap.put("board", board);
			resultMap.put("comments", commentList);
			return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			resultMap.put("message", FAIL);
			return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.NO_CONTENT);
		}
		
	}
	
	// update
	@ApiOperation(value="게시글을 수정해 보아요")
	@PutMapping("/{boardNo}")
	public ResponseEntity<String> updateBoard(@PathVariable("boardNo")int boardNo, @RequestBody BoardDto boardDto){
		logger.info("게시글을 수정해봅시다잉");
		try {
			boardService.updateBoard(boardNo, boardDto);
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
		}
	}

	// write
	@ApiOperation(value="자유게시판에 글을 작성해보아요")
	@PostMapping("/")
	public ResponseEntity<String> writeBoard(@RequestBody BoardDto boardDto){
		logger.debug("boardDto info: {}", boardDto);
		try {
			boardService.writeBoard(boardDto);
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="게시글을 삭제해 보아요")
	@DeleteMapping("/{boardNo}")
	public ResponseEntity<String> deleteBoard(@PathVariable("boardNo") int boardNo){
		logger.info("게시글 삭제할게요");
		try {
			boardService.deleteBoard(boardNo);
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * 댓글 CRD 코드 구간입니다.
	 *
	 */
	
	@ApiOperation(value="댓글을 작성해 보아요")
	@PostMapping("/*/comment")
	public ResponseEntity<String> writeComment(@RequestBody CommentDto commentDto){
		logger.info("commentDto: {}", commentDto);
		try {
			boardService.writeComment(commentDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="댓글을 삭제해 보아요")
	@DeleteMapping("/*/comment/{commentNo}")
	public ResponseEntity<String> deleteComment(@PathVariable("commentNo")int commentNo){
		logger.info(" commentNo : {}", commentNo);
		try {
			boardService.deleteComment(commentNo);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	
}
