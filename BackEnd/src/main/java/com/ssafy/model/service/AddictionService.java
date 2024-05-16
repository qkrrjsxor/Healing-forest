package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.dto.Addiction;

public interface AddictionService {
	// 생성
	public int createAddiction(Addiction addiction); 
	
	// 전체 조회
	public List<Addiction> getAddictionList(String userId);
	
	// 상세 조회
	public Addiction getAddictionDetail(String userId, int addictionId);
	
	// 삭제
	public int removeAddiction(int addictionId);

}
