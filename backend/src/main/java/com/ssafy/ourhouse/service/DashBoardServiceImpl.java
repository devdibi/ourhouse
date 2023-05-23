package com.ssafy.ourhouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ourhouse.mapper.DashBoardMapper;

@Service
public class DashBoardServiceImpl implements DashBoardService{
	
	private DashBoardMapper dashBoardMapper;
	
	
	public DashBoardServiceImpl(DashBoardMapper dashBoardMapper) {
		super();
		this.dashBoardMapper = dashBoardMapper;
	}


	@Override
	public List<Integer> getAverages(long dongCode) throws Exception {
		return dashBoardMapper.getAverages(dongCode);
	}


	@Override
	public List<Integer> getYearAmount(long dongCode) throws Exception {
		return dashBoardMapper.getYearAmount(dongCode);
	}


	@Override
	public List<Integer> getMonthAmount(long dongCode) throws Exception {
		return dashBoardMapper.getMonthAmount(dongCode);
	}


	@Override
	public List<Object> getPolygon(long dongCode) throws Exception {
		return dashBoardMapper.getPolygon(dongCode);
	}
	
}
