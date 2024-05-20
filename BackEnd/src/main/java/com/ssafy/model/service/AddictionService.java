package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.dto.Addiction;
import com.ssafy.model.dto.AddictionDetail;
import com.ssafy.model.dto.Badge;

public interface AddictionService {
	// 생성
	public int createAddiction(Addiction addiction); 
	
	// 전체 조회
	public List<Addiction> getAddictionList(String userId);
	
	// 상세 조회
	public AddictionDetail getAddictionDetail(String userId, int addictionId);
	
	// 업데이트
	public int updateAddiction(int addictionId, int targetTime);
	
	// 삭제
	public int removeAddiction(int addictionId);
	
	// 뱃지 생성
	public int createBadge(String userId, int addictionId);
	
	// 뱃지 조회
	public List<Badge> selectBadge(int addictionId);
	
	// 아이콘 수정
	public int updateIconAddiction(int addictionId, String iconPath);

}
