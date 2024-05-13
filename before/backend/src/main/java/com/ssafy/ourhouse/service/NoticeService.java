package com.ssafy.ourhouse.service;

import java.util.List;

import com.ssafy.ourhouse.dto.NoticeDto;

public interface NoticeService {
	List<NoticeDto> getNotices() throws Exception;
	
	void writeNotice(NoticeDto noticeDto) throws Exception;
	
	NoticeDto getNotice(int noticeNo) throws Exception;
	
	void updateNotice(int noticeNo, NoticeDto noticeDto) throws Exception;
	
	void deleteNotice(int noticeNo) throws Exception;
	
	void updateHit(int noticeNo) throws Exception;
	
}
