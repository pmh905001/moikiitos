package com.hyxc.moikiitos.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

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
