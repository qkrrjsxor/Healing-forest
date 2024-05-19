package com.ssafy.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.model.dto.Addiction;
import com.ssafy.model.dto.Badge;

import io.swagger.v3.oas.annotations.Parameter;

public interface AddictionDao {

	// 생성
	public int insertAddiction(Addiction addiction);
	
	// 전체 조회
	public List<Addiction> selectAddictionList(String userId);

	// 상세 조회
	public Addiction selectAddictionOne(@Param("addictionId") int addictionId);
	
	// 뱃지 조회
	public List<Badge> selectBadge(int addictionId);
	// 뱃지 생성
	public void insertBadge(@Param("userId") String userId, @Param("addictionId") int addictionId, @Param("date") int date );

	// 업데이트
	public int updateAddiction(@Param("addictionId") int addictionId, @Param("targetTime") int targetTime);
	
	// 삭제
	public int deleteAddiction(int addictionId);



}
