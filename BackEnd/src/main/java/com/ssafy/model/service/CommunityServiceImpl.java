package com.ssafy.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dao.CommunityDao;
import com.ssafy.model.dto.Comment;
import com.ssafy.model.dto.User;

@Service
public class CommunityServiceImpl implements CommunityService {

	// dao 의존성
	private final CommunityDao communityDao;
	
	public CommunityServiceImpl(CommunityDao communityDao) {
		this.communityDao = communityDao;
	}
	
	
	// 점수 상위 3 조회
	@Override
	public List<User> getRank() {
		List<User> ranker = communityDao.selectRank();
		
		return ranker;
	}
	
	// 내 점수 조회
	@Override
	public int getMyScore(String userId) {
		int score = communityDao.selectMyScore(userId);
		return score;
	}
	
	// 댓글 생성
	@Override
	public int createComment(String userId, String content) {
		
		return communityDao.insertComment(userId, content);
	}

	// 댓글 조회
	@Override
	public List<Comment> getCommentAll() {
		
		List<Comment> commentList = communityDao.selectCommentAll();
		return commentList;
	}

	// 댓글 수정
	@Override
	public int updateComment(int commentId, String content) {
		return communityDao.updateComment(commentId, content);
	}

	// 댓글 삭제
	@Override
	public int deleteComment(int commentId) {
		return communityDao.deleteComment(commentId);
	}


}
