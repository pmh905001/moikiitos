package com.hyxc.moikiitos.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class FollowerKey implements Serializable {
	
	private static final long serialVersionUID = -1039658742412245308L;

	@OneToOne
	private BlogUser target;
	
	@OneToOne
	private BlogUser follower;
	
	public BlogUser getTarget() {
		return target;
	}
	
	public void setTarget(BlogUser target) {
		this.target = target;
	}
	
	public BlogUser getFollower() {
		return follower;
	}
	
	public void setFollower(BlogUser follower) {
		this.follower = follower;
	}
	
}
