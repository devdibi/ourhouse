package com.ssafy.ourhouse.service;

import java.util.List;

public interface DashBoardService {
	List<Integer> getAverages(long dongCode) throws Exception;
	List<Integer> getYearAmount(long dongCode) throws Exception;
	List<Integer> getMonthAmount(long dongCode) throws Exception;
	List<Object> getPolygon(long dongCode) throws Exception;
}
