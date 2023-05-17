package com.hyxc.moikiitos.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

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
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
