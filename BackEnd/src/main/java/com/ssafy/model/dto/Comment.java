package com.ssafy.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Comment {
	private int commentId;
	private String userId;
	private String content;
	private LocalDateTime createDate;
	
	public Comment() {}
	
	public Comment(String userId, String content) {
		super();
		this.userId = userId;
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
