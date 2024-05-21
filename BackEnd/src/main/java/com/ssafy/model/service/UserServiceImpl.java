package com.ssafy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dao.UserDao;
import com.ssafy.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	
	@Autowired
	public UserServiceImpl (UserDao userDao) {
		this.userDao = userDao;
	}
	
	// 회원 가입
	@Override
	public int signup(User user) {
		return userDao.insertUser(user);
	}
	
	// 로그인
	@Override
	public User login(String userId) {
		return userDao.selectUser(userId);
	}

//	@Autowired
//    private PasswordEncoder passwordEncoder;
//
//	@Override
//    public User login(String userId, String password) {
//        User user = userDao.selectUser(userId);
//        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//            return user;
//        }
//        return null;
//    }
//
//	@Override
//    public User signup(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userDao.insertUser(user);
//        return user;
//    }
}
