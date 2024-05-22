package com.ssafy.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Comment {
	private int commentId;
	private String userId;
	private String nickname;
	private String content;
	private LocalDateTime createDate;
	
	public Comment() {}
	
	public Comment(String userId, String nickname, String content) {
		super();
		this.userId = userId;
		this.nickname = nickname;
		this.content = content;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getUserId() {
		return userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	
	
}
