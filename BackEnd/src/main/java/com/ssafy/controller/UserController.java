package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.model.dto.User;
import com.ssafy.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api-user")
public class UserController {
	
	// 의존성
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
		
	// 회원가입
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@ModelAttribute User user){
		userService.signup(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	// 2. 로그인
	@PostMapping("/login")
	public ResponseEntity<?> logIn(@RequestBody User user, HttpSession session) {
		User loginUser = userService.login(user.getUserId());

		if (loginUser == null) {
			return new ResponseEntity<String>("해당 유저는 존재하지 않습니다.", HttpStatus.NOT_FOUND);
		} else {
			if (!user.getPassword().equals(loginUser.getPassword())) {
				return new ResponseEntity<String>("비밀 번호가 틀렸습니다.", HttpStatus.UNAUTHORIZED);
			} else {
				session.setAttribute("loginUser", loginUser.getNickname());
				return new ResponseEntity<User>(loginUser, HttpStatus.OK);
			}
		}
	}
	
	// 3. 로그아웃
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		if (session.getAttribute("loginUser") != null) {
			session.invalidate();
			return new ResponseEntity<String>("로그아웃되었습니다.", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("로그인한 유저가 없습니다.", HttpStatus.UNAUTHORIZED);
		}
		
	}
}
