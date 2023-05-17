package com.hyxc.moikiitos.controllers;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyxc.moikiitos.domain.BlogUser;
import com.hyxc.moikiitos.domain.Post;
import com.hyxc.moikiitos.dto.UserPostDto;
import com.hyxc.moikiitos.services.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String ERROR_MESSAGE = "errorMessage";

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Parse an integer from a String. Returns zero when it's not an integer and
	 * when it's less than zero.
	 */
	protected Integer readPageNumber(final String page) {
		Integer pageNumber = 0;
		try {
			pageNumber = Integer.valueOf(page);
		} catch (NumberFormatException ex) {
			// it's safe to eat this exception, since we correct for non-integers
			logger.warn(String.format("Page number input was invalid: %s", page));
		}
		return (pageNumber >= 0) ? pageNumber : 0;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Map<String, Object> model) {
		if (!model.containsKey("blogUser")) {
			model.put("blogUser", new BlogUser());
		}
		return "register";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		return "error";
	}

	@RequestMapping(value = "/errorNotFound", method = RequestMethod.GET)
	public String errorNotFound() {
		return "errorNotFound";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String createUser(@Valid BlogUser blogUser, BindingResult result, Map<String, Object> model) {

		String view = "";

		if (!result.hasErrors()) {
			String username = blogUser.getUsername();
			String password = blogUser.getPassword();
			String email = blogUser.getEmail();
			if (userService.userExists(username, email)) {
				view = "invalid";
				model.put(ERROR_MESSAGE, String.format(
						"Could not register the username %s because it has already been taken by another user.",
						username));
			} else {
				String errorMessage = userService.registerUser(username, password, email);
				if (StringUtils.isBlank(errorMessage)) {
					view = "login";
					model.put("message", String.format(
							"Thank you for registering, %s. You can now login using your new account.", username));
				} else {
					view = "error";
					model.put(ERROR_MESSAGE, errorMessage);
				}
			}
		} else {
			logger.debug("Registering new BlogUser failed validation.");
			model.put("blogUser", blogUser);
			view = register(model);
		}
		return view;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showPostsFromFollowers(@RequestParam(defaultValue = "0") final String page, Map<String, Object> model,
			final Principal principal) {
		String myUsername = principal.getName();
		Page<Post> posts = userService.getAllFollowersPostsForUser(myUsername, readPageNumber(page));
		model.put("posts", posts);

		if (!model.containsKey("post")) {
			model.put("post", new Post());
		}
		return "createPost";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String doMessagePost(@Valid Post post, BindingResult result,
			@RequestParam(defaultValue = "0") final String page, Map<String, Object> model, final Principal principal) {

		String view = "";
		String myUsername = principal.getName();

		if (!result.hasErrors()) {
			String errorMessage = userService.createPost(myUsername, post);

			if (StringUtils.isBlank(errorMessage)) {
				model.put("message", "Post created successfully");
				view = "createPost";
			} else {
				model.put(ERROR_MESSAGE, errorMessage);
				view = "error";
			}
		} else {
			model.put("post", post);
			view = "createPost";
		}

		showPostsFromFollowers(page, model, principal);
		return view;
	}

	/**
	 * Return all the follower's posts for the logged-in user, in JSON format.
	 */
	@RequestMapping(value = "/livePosts", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<UserPostDto> getPosts(final Long createdAfter, final Principal principal) {
		Date createdAfterDate = new Date(createdAfter);
		return userService.getFollowersPostsForUser(principal.getName(), createdAfterDate);
	}

	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public String showPostsForOneUser(final String username, @RequestParam(defaultValue = "0") final String page,
			Map<String, Object> model) {
		String view = "posts";
		if (!StringUtils.isBlank(username)) {
			if (userService.userExists(username, null)) {
				Page<Post> posts = userService.getAllPostsForUsers(Arrays.asList(username), readPageNumber(page));
				model.put("posts", posts);
				model.put("username", username);
			} else {
				model.put(ERROR_MESSAGE, String.format("The username %s does not exist.",username) );
				view = "invalid";
			}
		}
		return view;
	}
}
