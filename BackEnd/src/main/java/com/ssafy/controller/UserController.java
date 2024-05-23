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
	public ResponseEntity<?> signup(@RequestBody User user) {
		int result = 0;
		
		try {
			result = userService.signup(user);
		} catch (Exception e) {
			return new ResponseEntity<String>("이미 존재하는 아이디입니다.", HttpStatus.BAD_REQUEST);
		}
		
		if(result == 1) {
			return new ResponseEntity<String>("회원가입 성공", HttpStatus.OK);			
		}else {
			return new ResponseEntity<String>("회원가입 실패", HttpStatus.BAD_REQUEST);
		}
	}

	// 2. 로그인
	@PostMapping("/login")
	public ResponseEntity<?> logIn(@RequestBody User user, HttpSession session) {
		User loginUser = userService.login(user.getUserId());

		if (loginUser == null) {
			return new ResponseEntity<String>("존재하지 않는 아이디입니다.", HttpStatus.UNAUTHORIZED);
		} else {
			if (!user.getPassword().equals(loginUser.getPassword())) {
				return new ResponseEntity<String>("비밀번호가 틀렸습니다.", HttpStatus.UNAUTHORIZED);
			} else {
				session.setAttribute("loginUser", loginUser.getUserId());
				session.setAttribute("nickname", loginUser.getNickname());	//nickname 추가
				System.out.println(session.getAttribute("loginUser"));
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
