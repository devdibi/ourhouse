package com.ssafy.ourhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DashBoardMapper {
	List<Integer> getAverages(long dongCode) throws SQLException;
	List<Integer> getYearAmount(long dongCode) throws SQLException;
	List<Integer> getMonthAmount(long dongCode) throws SQLException;
	List<Object> getPolygon(long dongCode) throws SQLException;
}
