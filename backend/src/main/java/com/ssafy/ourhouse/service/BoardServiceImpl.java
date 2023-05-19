package com.ssafy.ourhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ourhouse.dto.BoardDto;
import com.ssafy.ourhouse.dto.CommentDto;
import com.ssafy.ourhouse.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardMapper boardMapper;
	
	public BoardServiceImpl(BoardMapper boardMapper) {
		super();
		this.boardMapper = boardMapper;
	}

	@Override
	public List<BoardDto> getBoards() throws Exception {
		return boardMapper.getBoards();
	}

	@Override
	public void writeBoard(BoardDto boardDto) throws Exception {
		boardMapper.writeBoard(boardDto);
	}

	@Override
	public BoardDto getBoard(int boardNo) throws Exception {
		return boardMapper.getBoard(boardNo);
	}

	@Override
	public void updateBoard(int boardNo, BoardDto boardDto) throws Exception {
		boardMapper.updateBoard(boardNo, boardDto);
	}

	@Override
	public void deleteBoard(int boardNo) throws Exception {
		boardMapper.deleteBoard(boardNo);		
	}

	@Override
	public void updateHit(int boardNo) throws Exception {
		boardMapper.updateHit(boardNo);		
	}

	@Override
	public List<CommentDto> getComment(int boardNo) throws Exception {
		return boardMapper.getComment(boardNo);
	}

	@Override
	public void writeComment(CommentDto commentDto) throws Exception {
		boardMapper.writeComment(commentDto);
	}

	@Override
	public void deleteComment(int commentNo) throws Exception {
		boardMapper.deleteComment(commentNo);
	}
	
}
