package com.ssafy.ourhouse.service;

import java.util.List;

import com.ssafy.ourhouse.dto.DongDto;
import com.ssafy.ourhouse.dto.HouseDto;
import com.ssafy.ourhouse.dto.HouseSearchConditionDto;
import com.ssafy.ourhouse.dto.SidoDto;
import com.ssafy.ourhouse.dto.SigunguDto;

public interface HouseService {

	List<HouseDto> houseSearch(HouseSearchConditionDto searchCondition) throws Exception;

	List<SidoDto> getSido() throws Exception;

	List<SigunguDto> getSigungu(String sido) throws Exception;

	List<DongDto> getDong(String dong) throws Exception;

	void houseLike(String userEmail, String aptCode) throws Exception;

	void dealLike(String userEmail, String dealCode) throws Exception;
	
	void houseDislike(String userEmail, String aptCode) throws Exception;
	
	void dealDislike(String userEmail, String dealCode) throws Exception;

}