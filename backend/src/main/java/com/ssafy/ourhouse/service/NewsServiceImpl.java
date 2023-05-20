package com.ssafy.ourhouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ourhouse.dto.NewsDto;
import com.ssafy.ourhouse.mapper.NewsMapper;

@Service
public class NewsServiceImpl implements NewsService {
	
	private NewsMapper newsMapper;
	
	public NewsServiceImpl(NewsMapper newsMapper) {
		super();
		this.newsMapper = newsMapper;
	}

	@Override
	public List<NewsDto> getNews() throws Exception {
		return newsMapper.getNews();
	}

	@Override
	public void updateHit(int newsNo) throws Exception {
		newsMapper.updateHit(newsNo);
	}

	@Override
	public void updateNews(NewsDto news) throws Exception {
		newsMapper.updateNews(news);
	}

}
