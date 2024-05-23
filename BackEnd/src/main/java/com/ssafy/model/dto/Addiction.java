package com.ssafy.model.dto;

import java.util.Date;

public class Addiction {
	private int addictionId;
	private String userId;
	private String title;
	private Date startTime;
	private int targetTime;
	private Date endTime;
	private String iconPath;
	private int addictionScore = 0; // default value
	private boolean success = false;

	public Addiction() {
	}

	// 중독 리스트 생성 시
	public Addiction(String userId, String title, int targetTime, String iconPath) {
		this.userId = userId;
		this.title = title;
		this.targetTime = targetTime;
		this.iconPath = iconPath;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public int getAddictionId() {
		return addictionId;
	}

	public void setAddictionId(int addictionId) {
		this.addictionId = addictionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(int targetTime) {
		this.targetTime = targetTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getAddictionScore() {
		return addictionScore;
	}

	public void setAddictionScore(int addictionScore) {
		this.addictionScore = addictionScore;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
