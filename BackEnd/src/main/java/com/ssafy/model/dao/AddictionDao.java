package com.ssafy.model.dao;

import java.util.List;

import com.ssafy.model.dto.Addiction;

public interface AddictionDao {

	// 생성
	public int insertAddiction(Addiction addiction);
	
	// 전체 조회
	public List<Addiction> selectAddictionList(String userId);


	// 상세 조회
	
	// 삭제
	public int deleteAddiction(int addictionId);
}
