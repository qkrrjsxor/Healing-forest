package com.ssafy.model.dto;

public class Badge {
	private int badgeId;
	private String userId;
	private int addictionId;
	private int badgeDate;
	private int badgeScore;
	private String badgeImgUrl;
	
	public Badge() {}
	
	public Badge(int badgeDate) {
		super();
		this.badgeDate = badgeDate;
		this.badgeScore = badgeDate*10;
		
		//badgeDate별 url select
		switch (badgeDate) {
			case 1: {
				badgeImgUrl = "url1";
				break;
			}
			case 2: {
				badgeImgUrl = "url2";
				break;
			}
			case 3: {
				badgeImgUrl = "url3";
				break;
			}
			case 4: {
				badgeImgUrl = "url4";
				break;
			}
			case 5: {
				badgeImgUrl = "url5";
				break;
			}
			case 6: {
				badgeImgUrl = "url6";
				break;
			}
			case 7: {
				badgeImgUrl = "url7";
				break;
			}
			case 10: {
				badgeImgUrl = "url10";
				break;
			}
			case 14: {
				badgeImgUrl = "url14";
				break;
			}
			case 20: {
				badgeImgUrl = "url20";
				break;
			}
			case 30: {
				badgeImgUrl = "url30";
				break;
			}
			case 50: {
				badgeImgUrl = "url50";
				break;
			}
			case 70: {
				badgeImgUrl = "url70";
				break;
			}
			case 100: {
				badgeImgUrl = "url100";
				break;
			}
		}
		
		
	}

	public int getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(int badgeId) {
		this.badgeId = badgeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getAddictionId() {
		return addictionId;
	}

	public void setAddictionId(int addictionId) {
		this.addictionId = addictionId;
	}

	public int getBadgeDate() {
		return badgeDate;
	}

	public void setBadgeDate(int badgeDate) {
		this.badgeDate = badgeDate;
	}

	public int getBadgeScore() {
		return badgeScore;
	}

	public void setBadgeScore(int badgeScore) {
		this.badgeScore = badgeScore;
	}

	public String getBadgeImgUrl() {
		return badgeImgUrl;
	}

	public void setBadgeImgUrl(String badgeImgUrl) {
		this.badgeImgUrl = badgeImgUrl;
	}
	
	
	
	
}
