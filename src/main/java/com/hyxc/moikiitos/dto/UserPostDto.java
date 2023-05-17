package com.hyxc.moikiitos.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.hyxc.moikiitos.domain.Post;

public class UserPostDto implements Serializable {

	private static final long serialVersionUID = -6979971216116537530L;
	
	private String username;
	private String message;
	private String age;
	private Date createdDate;
	private Date retrievalDate;
	
	/**
	 * Static factory method to create a list of UserPostDto instances from a List of Post objects.
	 */
	public static List<UserPostDto> createUserPosts(List<Post> posts) {
		List<UserPostDto> userPosts = new ArrayList<UserPostDto>();
		for(Post post : posts) {
			UserPostDto userPostDto = new UserPostDto(post);
			userPosts.add(userPostDto);
		}
		return userPosts;
	}
	
	public UserPostDto(Post post) {
		username = post.getBlogUser().getUsername();
		message = post.getMessage();
		age = post.getAge();
		createdDate = post.getCreatedDate();
		retrievalDate = post.getRetrievalDate();
	}
	
	public String getUsername() {
		return username;
	}


	public String getMessage() {
		return message;
	}


	public String getAge() {
		return age;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getRetrievalDate() {
		return retrievalDate;
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
