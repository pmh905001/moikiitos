package com.hyxc.moikiitos.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.hyxc.moikiitos.domain.BlogUser;
import com.hyxc.moikiitos.domain.Post;
import com.hyxc.moikiitos.dto.UserPostDto;

public interface UserService {
	boolean userExists(String username,String email);
	String registerUser(String username, String plainTextPassword,String email);
	List<String> searchForUsers(String username);
	String createPost(String username, Post post);
	List<UserPostDto> getFollowersPostsForUser(String username, Date createdAfter);
	Page<Post> getAllPostsForUsers(List<String> usernames, int pageNumber);
	Page<Post> getAllFollowersPostsForUser(String username, int pageNumber);
	String addFollower(String targetUsername, String followerUsername);
	String removeFollower(String targetUsername, String followerUsername);
	List<String> getFollowingList(String username);
	List<BlogUser> getFollowingUserList(String username);
	List<BlogUser> getFollowerUserList(String username);
}
