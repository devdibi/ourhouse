package com.ssafy.ourhouse.service;

import java.util.List;

import com.ssafy.ourhouse.dto.NewsDto;

public interface NewsService {
	List<NewsDto> getNews() throws Exception;
	
	void updateHit(int newsNo) throws Exception;
	
	void updateNews(List<NewsDto> newsList) throws Exception;
}
