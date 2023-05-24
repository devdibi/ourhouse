package com.ssafy.ourhouse.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.ourhouse.dto.BusStopDto;
import com.ssafy.ourhouse.dto.CommercialDto;
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

	void houseLike(Long aptCode, String jwt) throws Exception;

	void dealLike(Long dealCode, String jwt) throws Exception;
	
	void houseDislike(Long aptCode, String jwt) throws Exception;
	
	void dealDislike(Long dealCode, String jwt) throws Exception;
	
	List<BusStopDto> getBusStation() throws Exception;
	
	List<CommercialDto> getNearByCommercial(double lat, double lng) throws Exception;
}
