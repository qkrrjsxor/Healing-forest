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
		
		// 전체 조회시 뱃지 생성. 전체 중독 리스트에 대해 Id get 하며 createBadge
		for(Addiction addiction : addictionList) {
			
			createBadge(userId, addiction.getAddictionId());
		}
		

        // 뱃지 생성 후 score 등록하기
        List<Badge> badgeList = addictionDao.selectBadgeAll(userId);
        int score = 0;
        
        for(Badge badge : badgeList) {
        	score += badge.getBadgeScore();
        }
        
        addictionDao.updateUserScore(userId, score);
		return addictionList;
	}

	// 상세 조회
	@Override
	public AddictionDetail getAddictionDetail(String userId, int addictionId) {

		Addiction addiction = addictionDao.selectAddictionOne(addictionId);
		List<Badge> badges = addictionDao.selectBadge(addictionId);
		
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

		System.out.println("중독 리스트 삭제 : " + addictionId);
		return addictionDao.deleteAddiction(addictionId);
	}

	// 뱃지 생성
	@Override
	public int createBadge(String userId, int addictionId) {
		
		int[] badgeTimeList = {1, 2, 3, 4, 5, 6, 7, 10, 14, 20, 30, 50, 70, 100};
		
		// 현재 날짜 가져오기
        LocalDateTime nowTime = LocalDateTime.now();
        
        // addiction id의 start time
        Addiction addiction = addictionDao.selectAddictionOne(addictionId);
        Date temp = addiction.getStartTime();
        // Date를 Instant로 변환
        Instant instant = temp.toInstant();
        // Instant를 시스템 기본 시간대를 기준으로 LocalDateTime로 변환
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        // 한국 시간에 맞게 9시간 빼주기
        LocalDateTime startTime = localDateTime.minusHours(9);
        
        // 두 시간 사이의 차이를 Duration 객체로 계산
        Duration duration = Duration.between(startTime, nowTime);
        long hours = duration.toHours();
        long daysPassed = duration.toDays();
        
        int targetTime = addiction.getTargetTime();
        List<Badge> badgeList = addictionDao.selectBadge(addictionId);
        
        // 뱃지 생성 조건
        if(badgeList.size() == 0) {
        	for(int date : badgeTimeList) {
        		if(date > targetTime) {
        			break;
        		}
        		if(daysPassed < date) {
        			break;
        		}
        		addictionDao.insertBadge(userId, addictionId, date);
        	}
        }else {
        	int lastBadgeDate = badgeList.get(badgeList.size()-1).getBadgeDate();

        	for(int date : badgeTimeList) {
        		if(date <= lastBadgeDate) {
        			continue;
        		}
        		if(date > targetTime) {
        			break;
        		}
        		if(daysPassed < date) {
        			break;
        		}
        		addictionDao.insertBadge(userId, addictionId, date);
        	}
        }
        return 1;
	}

	// 뱃지 조회
	@Override
	public List<Badge> selectBadge(int addictionId) {
		
		List<Badge> badgeList = addictionDao.selectBadge(addictionId);
		return badgeList;
	}

	// 아이콘 수정
	@Override
	public int updateIconAddiction(int addictionId, String iconPath) {
		return addictionDao.updateIconAddiction(addictionId, iconPath);
	}



	
}
