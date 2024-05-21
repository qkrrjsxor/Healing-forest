package com.ssafy.model.dao;

import com.ssafy.model.dto.User;

//DB 친화적 작명
public interface UserDao {
	// 회원가입
	public int insertUser(User user);

	// 로그인
	public User selectUser(String userId);
}
