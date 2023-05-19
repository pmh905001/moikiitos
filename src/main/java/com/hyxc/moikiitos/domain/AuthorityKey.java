package com.hyxc.moikiitos.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AuthorityKey implements Serializable {
	private static final long serialVersionUID = -2087144042691277032L;

	@ManyToOne
	@JoinColumn(name="username")
	private BlogUser blogUser;

	@Column(nullable = false)
	private String authority;

	public BlogUser getBlogUser() {
		return blogUser;
	}

	public void setBlogUser(BlogUser blogUser) {
		this.blogUser = blogUser;
	}
	
	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}