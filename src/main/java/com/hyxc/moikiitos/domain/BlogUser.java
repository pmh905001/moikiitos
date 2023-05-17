package com.hyxc.moikiitos.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Table(name="users", uniqueConstraints=@UniqueConstraint(columnNames="username"))
public class BlogUser implements Serializable {
	private static final long serialVersionUID = 7342832593953711366L;
	
	private static final int MAX_USERNAME_SIZE = 20;
	private static final int MAX_EMAIL_SIZE = 80;
	private static final int ENCRYPTED_PASSWORD_SIZE = 80;
	
	
	public BlogUser() {
		
	}

	@Id
	@Size(min = 1, max=MAX_USERNAME_SIZE, message="Username {javax.validation.constraints.Size.message}")
	@Pattern(regexp = "[a-zA-Z0-9]*", message="Username must be all letters")
	private String username;
	
	@Size(min = 1, max=MAX_EMAIL_SIZE, message="Email can not over 80 characters")
	@Pattern(regexp = "^(.+)@(\\S+)$", message="Invalid email")
	private String email;
	
	// the max size is for the encrypted password, which is always 80 characters
	@Size(min = 1, max=ENCRYPTED_PASSWORD_SIZE, message="Password {javax.validation.constraints.Size.message}")
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private Boolean enabled;
	
	@OneToMany
	@JoinColumn(name="username")
	private List<Post> posts;
	

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
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
