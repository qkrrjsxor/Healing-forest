package com.ssafy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.model.dto.Addiction;
import com.ssafy.model.dto.AddictionDetail;
import com.ssafy.model.service.AddictionService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api-addiction")
public class AddictionController {
		
	//서비스 의존성 주입
	private final AddictionService addictionService;
	
	public AddictionController(AddictionService addictionService) {
		this.addictionService = addictionService;
	}
	
	// 생성
	@PostMapping("/addiction")
	public ResponseEntity<?> createAddiction(@RequestBody Addiction addiction, HttpSession session){
		
//		System.out.println("controller" + session.getAttribute("loginUser"));
		addiction.setUserId((String) session.getAttribute("loginUser"));
		
		int result = addictionService.createAddiction(addiction);
		
		if (result == 1) {
			return new ResponseEntity<>(addiction, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("생성 실패", HttpStatus.BAD_REQUEST);
		}
	}
	
	// 전체 조회
	@GetMapping("/addiction")
	public ResponseEntity<?> getAddictionList(HttpSession session){
		// 세션에 저장된 로그인 정보 아이디 가져오기
		String userId = (String) session.getAttribute("loginUser");
		
		List<Addiction> addictionList = addictionService.getAddictionList(userId);
		
		if(addictionList == null) {
			return new ResponseEntity<>("리스트가 비어있습니다. 관리할 중독 리스트를 추가하세요", HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(addictionList, HttpStatus.OK);
		}
	}
	
	// 상세 조회
	@GetMapping("/addiction/{addictionId}")
	public ResponseEntity<?> getAddictionDetail(@PathVariable("addictionId") int addictionId, HttpSession session){
		// 세션에 저장된 로그인 정보 아이디 가져오기
		String userId = (String) session.getAttribute("loginUser");
		
		AddictionDetail addictionDetail = addictionService.getAddictionDetail(userId, addictionId);
				
//		System.out.println("상세조회 " + addictionDetail);
		
		if(addictionDetail == null) {
			return new ResponseEntity<>("해당 항목이 존재하지 않습니다.", HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(addictionDetail, HttpStatus.OK);
		}
	}
	
	// 업데이트
	@PutMapping("/addiction/{addictionId}")
	public ResponseEntity<?> updateAddiction(@PathVariable("addictionId") int addictionId, @RequestBody Addiction addiction){
		
		int targetTime = addiction.getTargetTime();
		int result = addictionService.updateAddiction(addictionId, targetTime);
		
		System.out.println("put test");
		if(result ==1) {
			return new ResponseEntity<>("업데이트 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("업데이트 실패", HttpStatus.BAD_REQUEST);
		}
	}
	
	// 삭제
	@DeleteMapping("/addiction/{addictionId}")
	public ResponseEntity<?> removeAddiction(@PathVariable("addictionId") int addictionId){
		int result = addictionService.removeAddiction(addictionId);
		
		if(result ==1) {
			return new ResponseEntity<>("삭제 완료", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("존재하지 않는 리스트입니다.", HttpStatus.BAD_REQUEST);
		}
	}
	
	// 업데이트
	@PutMapping("/addiction/icon/{addictionId}")
	public ResponseEntity<?> updateIconAddiction(@PathVariable("addictionId") int addictionId, @RequestBody Addiction addiction){
		
		String iconPath = addiction.getIconPath();
		int result = addictionService.updateIconAddiction(addictionId, iconPath);
		
		System.out.println("icon update test");
		if(result ==1) {
			return new ResponseEntity<>("업데이트 성공", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("업데이트 실패", HttpStatus.BAD_REQUEST);
		}
	}
}
