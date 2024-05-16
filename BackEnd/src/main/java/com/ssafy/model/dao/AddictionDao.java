package com.ssafy.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.model.dto.Addiction;

public interface AddictionDao {

	// 생성
	public int insertAddiction(Addiction addiction);
	
	// 전체 조회
	public List<Addiction> selectAddictionList(String userId);

	// 상세 조회
	public Addiction selectAddictionOne(@Param("userId") String userId, @Param("addictionId") int addictionId);
	
	// 삭제
	public int deleteAddiction(int addictionId);

}
