package com.hyxc.moikiitos.domain;

public enum FollowAction {
	FOLLOW("follow"),
	UNFOLLOW("unfollow");
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	private FollowAction(String message) {
		this.message = message;
	}
}
