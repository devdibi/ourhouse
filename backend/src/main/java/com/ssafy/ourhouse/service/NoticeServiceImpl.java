package com.ssafy.ourhouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ourhouse.dto.NoticeDto;
import com.ssafy.ourhouse.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {

	private NoticeMapper NoticeMapper;
	
	
	public NoticeServiceImpl(NoticeMapper NoticeMapper) {
		super();
		this.NoticeMapper = NoticeMapper;
	}

	@Override
	public List<NoticeDto> getNotices() throws Exception {
		return NoticeMapper.getNotices();
	}

	@Override
	public void writeNotice(NoticeDto NoticeDto) throws Exception {
		NoticeMapper.writeNotice(NoticeDto);
	}

	@Override
	public NoticeDto getNotice(int NoticeNo) throws Exception {
		return NoticeMapper.getNotice(NoticeNo);
	}

	@Override
	public void updateNotice(int NoticeNo, NoticeDto NoticeDto) throws Exception {
		NoticeMapper.updateNotice(NoticeNo, NoticeDto);
	}

	@Override
	public void deleteNotice(int NoticeNo) throws Exception {
		NoticeMapper.deleteNotice(NoticeNo);		
	}

	@Override
	public void updateHit(int NoticeNo) throws Exception {
		NoticeMapper.updateHit(NoticeNo);		
	}

	
}
