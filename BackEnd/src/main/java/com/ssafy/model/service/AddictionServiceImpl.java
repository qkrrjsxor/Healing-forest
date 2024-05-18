package com.ssafy.model.service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dao.AddictionDao;
import com.ssafy.model.dto.Addiction;
import com.ssafy.model.dto.AddictionDetail;
import com.ssafy.model.dto.Badge;

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
	@Override
	public AddictionDetail getAddictionDetail(int addictionId) {
		Addiction addiction = addictionDao.selectAddictionOne(addictionId);
		List<Badge> badges = addictionDao.selectBadge(addictionId);
		
		// 현재 날짜 가져오기
        LocalDateTime nowTime = LocalDateTime.now();
        
        // addiction id의 start time
        Date temp = addictionDao.selectAddictionOne(addictionId).getStartTime();
        // Date를 Instant로 변환
        Instant instant = temp.toInstant();
        // Instant를 시스템 기본 시간대를 기준으로 LocalDateTime로 변환
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        // 한국 시간에 맞게 9시간 빼주기
        LocalDateTime startTime = localDateTime.minusHours(9);
        
        // 두 시간 사이의 차이를 Duration 객체로 계산
        Duration duration = Duration.between(startTime, nowTime);
        long hours = duration.toHours();
        
        System.out.println("now : " + nowTime);
        System.out.println("start : " + startTime);
        
        System.out.println(hours);
		return new AddictionDetail(addiction, badges);
	}
	
	// 업데이트
	@Override
	public int updateAddiction(int addictionId, int targetTime) {
		
		return addictionDao.updateAddiction(addictionId, targetTime);
	}
	
	// 삭제
	@Override
	public int removeAddiction(int addictionId) {

		return addictionDao.deleteAddiction(addictionId);
	}

	// 뱃지 생성
	@Override
	public int createBadge(int addictionId) {
		
		// 현재 날짜 가져오기
        LocalDateTime nowTime = LocalDateTime.now();
        
        // addiction id의 start time
        Date temp = addictionDao.selectAddictionOne(addictionId).getStartTime();
        // Date를 Instant로 변환
        Instant instant = temp.toInstant();
        // Instant를 시스템 기본 시간대를 기준으로 LocalDateTime로 변환
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        // 한국 시간에 맞게 9시간 빼주기
        LocalDateTime startTime = localDateTime.minusHours(9);
        
        // 두 시간 사이의 차이를 Duration 객체로 계산
        Duration duration = Duration.between(startTime, nowTime);
        long hours = duration.toHours();
        
        List<Badge> badgeList = addictionDao.selectBadge(addictionId);
        
        for(Badge badge : badgeList) {
        	int badgeTime = badge.getBadgeDate();
        }
		return 0;
	}

	// 뱃지 조회
	@Override
	public List<Badge> selectBadge(int addictionId) {
		
		List<Badge> badgeList = addictionDao.selectBadge(addictionId);
		return badgeList;
	}



	
}
