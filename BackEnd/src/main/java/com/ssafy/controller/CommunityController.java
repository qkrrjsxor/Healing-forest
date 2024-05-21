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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.model.dto.Comment;
import com.ssafy.model.dto.User;
import com.ssafy.model.service.CommunityService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api-community")
public class CommunityController {

	// service 의존성 주입
	private final CommunityService communityService;

	public CommunityController(CommunityService communityService) {
		this.communityService = communityService;
	}

	// 랭크 top 3 조회
	@GetMapping("/rank")
	public ResponseEntity<?> getRank() {
		List<User> ranker = communityService.getRank();

		if (ranker == null) {
			return new ResponseEntity<>("랭커 조회 실패", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ranker, HttpStatus.OK);
	}

	// 내 점수 조회
	@GetMapping("/myscore")
	public ResponseEntity<?> getMyScore(HttpSession session) {
		String userId = (String) session.getAttribute("loginUser");
		
		int score = communityService.getMyScore(userId);
		
		return new ResponseEntity<>(score, HttpStatus.OK);
	}

	// 댓글 등록
	@PostMapping("/comment")
	public ResponseEntity<?> createComment(@RequestBody String comment, HttpSession session) {
		// login user 정보
		String userId = (String) session.getAttribute("loginUser");
		Comment newComment = communityService.createComment(userId, comment);

		if (newComment != null) {
			return new ResponseEntity<Comment>(newComment, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("댓글 등록 실패", HttpStatus.BAD_REQUEST);
		}
	}

	// 댓글 조회
	@GetMapping("/comment")
	public ResponseEntity<?> getCommentAll() {
		List<Comment> commentList = communityService.getCommentAll();

		if (commentList == null) {
			return new ResponseEntity<>("no content", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(commentList, HttpStatus.OK);
		}
	}

	// 댓글 수정
	@PutMapping("/comment/{commentId}")
	public ResponseEntity<?> updateComment(@PathVariable("commentId") int commentId, @RequestBody Comment comment) {
		int result = communityService.updateComment(commentId, comment.getContent());

		if (result == 1) {
			return new ResponseEntity<>("댓글 수정 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("댓글 수정 실패", HttpStatus.BAD_REQUEST);
		}
	}

	// 댓글 삭제
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable("commentId") int commentId) {
		int result = communityService.deleteComment(commentId);

		if (result == 1) {
			return new ResponseEntity<>("댓글 삭제 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("댓글 삭제 실패", HttpStatus.BAD_REQUEST);
		}
	}
}
