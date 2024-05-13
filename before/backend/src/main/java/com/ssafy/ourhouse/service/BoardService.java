package com.ssafy.ourhouse.service;

import java.util.List;

import com.ssafy.ourhouse.dto.BoardDto;
import com.ssafy.ourhouse.dto.CommentDto;

public interface BoardService {
	List<BoardDto> getBoards() throws Exception;
	
	void writeBoard(BoardDto boardDto) throws Exception;
	
	BoardDto getBoard(int boardNo) throws Exception;
	
	void updateBoard(int boardNo, BoardDto boardDto) throws Exception;
	
	void deleteBoard(int boardNo) throws Exception;
	
	void updateHit(int boardNo) throws Exception;
	
	// comment 영역
	
	List<CommentDto> getComment(int boardNo) throws Exception;
	
	void writeComment(CommentDto commentDto) throws Exception;
	
	void deleteComment(int commentNo) throws Exception;
}
