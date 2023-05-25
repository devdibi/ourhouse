package com.ssafy.ourhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ourhouse.dto.NoticeDto;

@Mapper
public interface NoticeMapper {
	List<NoticeDto> getNotices() throws SQLException;
	
	void writeNotice(NoticeDto noticeDto) throws SQLException;
	
	NoticeDto getNotice(int noticeNo) throws SQLException;

	void updateNotice(int noticeNo, NoticeDto noticeDto) throws SQLException;
	
	void deleteNotice(int noticeNo) throws SQLException;
	
	void updateHit(int noticeNo) throws SQLException;
	
	
}
