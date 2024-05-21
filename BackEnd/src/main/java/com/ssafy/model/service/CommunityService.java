package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.dto.Comment;
import com.ssafy.model.dto.User;

public interface CommunityService {
	
	// 점수 상위 3 조회
	public List<User> getRank();
	
	// 내 점수 조회
	public int getMyScore(String userId);
	
	// 댓글 등록
	public Comment createComment(String userId, String comment);
	
	// 댓글 전체 조회
	public List<Comment> getCommentAll();
	
	// 댓글 수정
	public int updateComment(int commentId, String content);
	
	// 댓글 삭제
	public int deleteComment(int commentId);
}
