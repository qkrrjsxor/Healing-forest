package com.ssafy.model.dto;

public class User {
	//camelCase 변수명
	private String userId;
    private String password;
    private String nickname;
    private String userImageUrl = "url";	//default value
    private int userScore = 0;	//default value
    
    public User() {
	}

	public User(String userId, String password, String nickname, String userImageUrl) {
		this.userId = userId;
		this.password = password;
		this.nickname = nickname;
		this.userImageUrl = userImageUrl;
	}
	
	public User(String userId, String password, String nickname) {
		super();
		this.userId = userId;
		this.password = password;
		this.nickname = nickname;
	}

	//getter setter
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUserImageUrl() {
		return userImageUrl;
	}
	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}
	public int getUserScore() {
		return userScore;
	}
	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}
    
    
}
