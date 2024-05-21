package com.ssafy.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.model.dto.Comment;
import com.ssafy.model.dto.User;

public interface CommunityDao {

	// 점수 상위 top 3 조회
	public List<User> selectRank();
	
	// 내 점수 조회
	public int selectMyScore(@Param("userId") String userId);
	
	// 댓글 삽입
	public int insertComment(@Param("userId") String userId, @Param("content") String content);

	// 댓글 조회
	public List<Comment> selectCommentAll();

	// 댓글 수정
	public int updateComment(@Param("commentId") int commentId, @Param("content") String content);

	// 댓글 삭제
	public int deleteComment(@Param("commentId") int commentId);
}
