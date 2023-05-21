package com.ssafy.ourhouse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ourhouse.dto.DealDto;
import com.ssafy.ourhouse.dto.DongDto;
import com.ssafy.ourhouse.dto.HouseDatabaseDto;
import com.ssafy.ourhouse.dto.HouseDto;
import com.ssafy.ourhouse.dto.HouseSearchConditionDto;
import com.ssafy.ourhouse.dto.SidoDto;
import com.ssafy.ourhouse.dto.SigunguDto;
import com.ssafy.ourhouse.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService {

	private HouseMapper houseMapper;

	public HouseServiceImpl(HouseMapper houseMapper) {
		super();
		this.houseMapper = houseMapper;
	}

	@Override
	public List<HouseDto> houseSearch(HouseSearchConditionDto searchCondition) throws Exception {

		// 거래 정보 뽑아오기
		List<HouseDatabaseDto> houseList = houseMapper.houseSearch(searchCondition);
		List<HouseDto> resultList = new ArrayList();
		System.out.println("출력 갯수 : " +houseList.size());
		for (HouseDatabaseDto house : houseList) {
			if (!resultList.contains(new HouseDto(house.getAptCode()))) {
				HouseDto tmp = new HouseDto();
				tmp.setAptCode(house.getAptCode());
				tmp.setBuildYear(house.getBuildYear());
				tmp.setDongCode(house.getDongCode());
				tmp.setJibunAddress(house.getJibunAddress());
				tmp.setLat(house.getLat());
				tmp.setLng(house.getLng());
				tmp.setName(house.getName());
				tmp.setRoadAddress(house.getRoadAddress());
				resultList.add(tmp);
			}
			int idx = resultList.indexOf(new HouseDto(house.getAptCode()));
			DealDto tmp = new DealDto();
			tmp.setArea(house.getArea());
			tmp.setDealCode(house.getDealCode());
			tmp.setFloor(house.getFloor());
			tmp.setMonth(house.getMonth());
			tmp.setPrice(house.getPrice());
			tmp.setYear(house.getYear());
			resultList.get(idx).addDeal(tmp);
		}

		// 관심 아파트 정보 뽑아 적용
		List<String> favoriteApts = houseMapper.houseLikes(searchCondition.getEmail());
		for (String aptCode : favoriteApts) {
			if (resultList.contains(new HouseDto(aptCode))) {
				int idx = resultList.indexOf(new HouseDto(aptCode));
				resultList.get(idx).setHouseLike(true);
			}
		}

		List<DealDto> favoriteDeals = houseMapper.dealLikes(searchCondition.getEmail());
		for(DealDto deal : favoriteDeals) {
			// 아파트 목록에 관심목록에 있는 아파트가 있을 시
			if(resultList.contains(new HouseDto(deal.getPrice()))) {
				int idx = resultList.indexOf(new HouseDto(deal.getPrice()));
				//거래목록 있는지 확인
				if(resultList.get(idx).getDeals().contains(deal)) {
					int idxDeal = resultList.get(idx).getDeals().indexOf(deal);
					resultList.get(idx).getDeals().get(idxDeal).setDealLike(true);
				}
			}
		}
		System.out.println("거래정보 추가 완료");
		return resultList;
	}

	@Override
	public List<SidoDto> getSido() throws Exception {
		return houseMapper.getSido();
	}

	@Override
	public List<SigunguDto> getSigungu(String sido) throws Exception {
		return houseMapper.getSigungu(sido);
	}

	@Override
	public List<DongDto> getDong(String dong) throws Exception {
		return houseMapper.getDong(dong);
	}

	@Override
	public void houseLike(String userEmail, String aptCode) throws Exception {
		houseMapper.houseLike(userEmail, aptCode);
	}

	@Override
	public void dealLike(String userEmail, String dealCode) throws Exception {
		houseMapper.dealLike(userEmail, dealCode);
	}
	
	@Override
	public void houseDislike(String userEmail, String aptCode) throws Exception {
		houseMapper.houseDislike(userEmail, aptCode);
	}

	@Override
	public void dealDislike(String userEmail, String dealCode) throws Exception {
		houseMapper.dealDislike(userEmail, dealCode);
	}

}