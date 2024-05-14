package com.ssafy.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dao.AddictionDao;
import com.ssafy.model.dto.Addiction;

@Service
public class AddictionServiceImpl implements AddictionService{

	//dao 의존성
	private final AddictionDao addictionDao;
	
	public AddictionServiceImpl(AddictionDao addictionDao){
		this.addictionDao = addictionDao;
	}
	
	// 생성
	@Override
	public int createAddiction(Addiction addiction) {
		return addictionDao.insertAddiction(addiction);
	}
	
	// 전체 조회
	@Override
	public List<Addiction> getAddictionList(String userId) {
		List<Addiction> addictionList = addictionDao.selectAddictionList(userId);
		
		return addictionList;
	}

	// 상세 조회
	
	// 삭제
	@Override
	public int removeAddiction(int addictionId) {

		return addictionDao.deleteAddiction(addictionId);
	}


	
}