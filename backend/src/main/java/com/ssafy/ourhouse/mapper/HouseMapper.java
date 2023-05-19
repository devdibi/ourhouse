package com.ssafy.ourhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ourhouse.dto.DealDto;
import com.ssafy.ourhouse.dto.DongDto;
import com.ssafy.ourhouse.dto.HouseDatabaseDto;
import com.ssafy.ourhouse.dto.HouseSearchConditionDto;
import com.ssafy.ourhouse.dto.SidoDto;
import com.ssafy.ourhouse.dto.SigunguDto;

@Mapper
public interface HouseMapper {

	List<HouseDatabaseDto> houseSearch(HouseSearchConditionDto searchCondition) throws SQLException;

	List<String> houseLikes(String email) throws SQLException;

	List<DealDto> dealLikes(String email) throws SQLException;

	List<SidoDto> getSido() throws SQLException;

	List<SigunguDto> getSigungu(String sido) throws SQLException;

	List<DongDto> getDong(String dong) throws SQLException;

	void houseLike(String userEmail, String aptCode)throws SQLException;

	void dealLike(String userEmail, String dealCode)throws SQLException;

	void houseDislike(String userEmail, String aptCode)throws SQLException;

	void dealDislike(String userEmail, String dealCode)throws SQLException;

}
