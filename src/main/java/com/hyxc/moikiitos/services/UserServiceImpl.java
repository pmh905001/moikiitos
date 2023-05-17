package com.hyxc.moikiitos.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.hyxc.moikiitos.domain.BlogUser;
import com.hyxc.moikiitos.domain.Follower;
import com.hyxc.moikiitos.domain.FollowerKey;
import com.hyxc.moikiitos.domain.Post;
import com.hyxc.moikiitos.dto.UserPostDto;
import com.hyxc.moikiitos.repositories.BlogUserRepository;
import com.hyxc.moikiitos.repositories.FollowerRepository;
import com.hyxc.moikiitos.repositories.PostRepository;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final int POSTS_PER_PAGE = 15;

	@Autowired
	private UserDetailsManager userDetailsManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private BlogUserRepository blogUserRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private FollowerRepository followerRepository;

	// These are the same for every user we create
	private static final boolean ENABLED = true;
	private static final boolean ACCOUNT_NOT_EXPIRED = true;
	private static final boolean CREDENTIALS_NOT_EXPIRED = true;
	private static final boolean ACCOUNT_NOT_LOCKED = true;
	private static final List<SimpleGrantedAuthority> AUTHORITIES = Arrays.asList(new SimpleGrantedAuthority("user"));

	/**
	 * Hash the plain text password and create a new UserDetails instance that can
	 * be persisted.
	 */
	private UserDetails initializeUser(String username, String plainTextPassword) {
		String password = passwordEncoder.encode(plainTextPassword);
		return new User(username, password, ENABLED, ACCOUNT_NOT_EXPIRED, CREDENTIALS_NOT_EXPIRED, ACCOUNT_NOT_LOCKED,
				AUTHORITIES);
	}

	public boolean userExists(String username, String email) {
		// Also support email account.
		return userDetailsManager.userExists(username);
	}

	/**
	 * This will try to save a new user, and returns an error message if the save
	 * failed. This does not check for duplicate usernames before saving.
	 */
	public String registerUser(String username, String plainTextPassword, String email) {
		String errorMessage = "";
		try {
			userDetailsManager.createUser(initializeUser(username, plainTextPassword));
			BlogUser blogUser = blogUserRepository.findByUsername(username);
			blogUser.setEmail(email);
			blogUserRepository.save(blogUser);
		} catch (DataIntegrityViolationException ex) {
			logger.error("DataIntegrityViolationException when saving user " + username + ".", ex);
			errorMessage = "Could not register user \"" + username + "\".  The user may already exist.";
		}
		return errorMessage;
	}

	public List<String> searchForUsers(String username) {
		List<String> usernames = new ArrayList<String>();
		List<BlogUser> blogUsers = blogUserRepository.findByUsernameLike(username);
		for (BlogUser blogUser : blogUsers) {
			usernames.add(blogUser.getUsername());
		}
		return usernames;
	}

	private BlogUser findBlogUserByUsername(String username) {
		return blogUserRepository.findByUsername(username);
	}

	public String createPost(String username, Post post) {

		BlogUser blogUser = findBlogUserByUsername(username);
		String errorMessage = "";

		if (blogUser != null) {
			post.setBlogUser(blogUser);
			post.setCreatedDate(new Date());

			try {
				postRepository.save(post);
			} catch (DataIntegrityViolationException ex) {
				logger.error("DataIntegrityViolationException when saving post for user " + username + ".", ex);
				errorMessage = "Could not save post for user \"" + username + "\".";
			}
		} else {
			errorMessage = "Could not save post because the user \"" + username + "\" could not be found.";
		}

		return errorMessage;
	}

	public List<UserPostDto> getFollowersPostsForUser(String username, Date createdAfter) {
		List<String> following = getFollowingList(username);
		// get posts for yourself as well
		following.add(username);

		List<Post> posts = null;
		posts = postRepository.findByUsernameIn(following, createdAfter);

		return UserPostDto.createUserPosts(posts);
	}

	public Page<Post> getAllPostsForUsers(List<String> usernames, int pageNumber) {
		return postRepository.findByUsernameIn(usernames, new PageRequest(pageNumber, POSTS_PER_PAGE));
	}

	public Page<Post> getAllFollowersPostsForUser(String username, int pageNumber) {
		List<String> following = getFollowingList(username);
		// get posts for yourself as well
		following.add(username);
		return getAllPostsForUsers(following, pageNumber);
	}

	public String addFollower(String targetUsername, String followerUsername) {
		String errorMessage = "";
		BlogUser targetBlogUser = findBlogUserByUsername(targetUsername);
		BlogUser followerBlogUser = findBlogUserByUsername(followerUsername);
		Follower follower = new Follower();
		FollowerKey followerKey = new FollowerKey();
		followerKey.setTarget(targetBlogUser);
		followerKey.setFollower(followerBlogUser);
		follower.setFollowerKey(followerKey);

		followerRepository.save(follower);
		return errorMessage;
	}

	public String removeFollower(String targetUsername, String followerUsername) {
		String errorMessage = "";
		BlogUser targetBlogUser = findBlogUserByUsername(targetUsername);
		BlogUser followerBlogUser = findBlogUserByUsername(followerUsername);
		Follower follower = new Follower();
		FollowerKey followerKey = new FollowerKey();
		followerKey.setTarget(targetBlogUser);
		followerKey.setFollower(followerBlogUser);
		follower.setFollowerKey(followerKey);

		followerRepository.delete(follower);
		return errorMessage;
	}

	public List<String> getFollowingList(String username) {
		return followerRepository.findByFollowerUsername(username);
	}

	@Override
	public List<BlogUser> getFollowerUserList(String username) {
		return followerRepository.findUserByTargetUsername(username);

	}

	@Override
	public List<BlogUser> getFollowingUserList(String username) {
		return followerRepository.findUserByFollowerUsername(username);
	}
}
