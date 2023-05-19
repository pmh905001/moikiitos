package com.hyxc.moikiitos.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Follower implements Serializable {

	private static final long serialVersionUID = -2922163470411084743L;

	@Id
	private FollowerKey followerKey;
	
	public FollowerKey getFollowerKey() {
		return followerKey;
	}

	public void setFollowerKey(FollowerKey followerKey) {
		this.followerKey = followerKey;
	}

}
