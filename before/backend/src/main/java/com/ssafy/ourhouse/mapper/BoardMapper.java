package com.ssafy.ourhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ourhouse.dto.BoardDto;
import com.ssafy.ourhouse.dto.CommentDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> getBoards() throws SQLException;
	
	void writeBoard(BoardDto boarDto) throws SQLException;
	
	BoardDto getBoard(int boardNo) throws SQLException;

	void updateBoard(int boardNo, BoardDto boardDto) throws SQLException;
	
	void deleteBoard(int boardNo) throws SQLException;
	
	void updateHit(int boardNo) throws SQLException;
	
	List<CommentDto> getComment(int boardNo) throws SQLException;
	
	// comment 영역
	void writeComment(CommentDto commentDto) throws SQLException;
	
	void deleteComment(int commentNo) throws SQLException;
	
	
}
