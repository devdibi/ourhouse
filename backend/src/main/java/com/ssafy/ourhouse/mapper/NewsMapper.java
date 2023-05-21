package com.ssafy.ourhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.json.JSONArray;

import com.ssafy.ourhouse.dto.NewsDto;

@Mapper
public interface NewsMapper {
	List<NewsDto> getNews() throws SQLException;
	
	void updateHit(int newsNo) throws SQLException;
	
	void updateNews(NewsDto news) throws SQLException;
}