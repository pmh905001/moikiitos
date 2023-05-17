package com.hyxc.moikiitos.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hyxc.moikiitos.domain.BlogUser;
import com.hyxc.moikiitos.domain.FollowAction;
import com.hyxc.moikiitos.services.UserService;

@Controller
public class FollowController {
	private static final Logger logger = LoggerFactory.getLogger(FollowController.class);
	private static final String ERROR_MESSAGE = "errorMessage";

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/following", method = RequestMethod.GET)
	public String following(Map<String, Object> model, final Principal principal) {
		String myUsername = principal.getName();
		List<BlogUser> following = userService.getFollowingUserList(myUsername);
		model.put("following", following);
		return "following";
	}

	@RequestMapping(value = "/followers", method = RequestMethod.GET)
	public String followers(Map<String, Object> model, final Principal principal) {
		String myUsername = principal.getName();
		List<BlogUser> followers = userService.getFollowerUserList(myUsername);
		model.put("followers", followers);
		return "followers";
	}

	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public String follow(Map<String, Object> model, final Principal principal) {
		String view = "follow";
		String myUsername = principal.getName();
		List<String> following = userService.getFollowingList(myUsername);
		model.put("following", following);
		return view;
	}

	private String changeFollower(final String targetUsername, Map<String, Object> model, final Principal principal,
			final FollowAction followAction) {
		String view = "invalid";
		String action = followAction.getMessage();
		if (StringUtils.isBlank(targetUsername)) {
			model.put(ERROR_MESSAGE, "Please specify a username to " + action);
		} else {

			if (userService.userExists(targetUsername, null)) {
				String myUsername = principal.getName();

				if (myUsername.equalsIgnoreCase(targetUsername)) {
					model.put(ERROR_MESSAGE, String.format("You cannot %s yourself.", action));
					view = "invalid";
				} else {
					String message = String.format("You have successfully %s user ", action, targetUsername);
					String errorMessage = "";

					if (followAction == FollowAction.FOLLOW) {
						userService.addFollower(targetUsername, myUsername);
					} else if (followAction == FollowAction.UNFOLLOW) {
						userService.removeFollower(targetUsername, myUsername);
					}

					if (StringUtils.isBlank(errorMessage)) {
						model.put("myUsername", myUsername);
						model.put("targetUsername", targetUsername);
						model.put("message", message);

						view = follow(model, principal);
					} else {
						model.put(ERROR_MESSAGE, errorMessage);
					}
				}
			} else {
				model.put(ERROR_MESSAGE,
						String.format("The user you are trying to %s %s does not exist.", action, targetUsername));
				view = "invalid";
			}
		}
		return view;
	}

	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public String doFollow(final String usernameToFollow, Map<String, Object> model, final Principal principal) {
		return changeFollower(usernameToFollow, model, principal, FollowAction.FOLLOW);
	}

	@RequestMapping(value = "/unfollow", method = RequestMethod.POST)
	public String unfollow(final String usernameToUnfollow, Map<String, Object> model, final Principal principal) {
		return changeFollower(usernameToUnfollow, model, principal, FollowAction.UNFOLLOW);
	}

	@RequestMapping(value = "/findUser", method = RequestMethod.GET)
	public String findUser() {
		return "user_search";
	}

	@RequestMapping(value = "/findUser", method = RequestMethod.POST)
	public String findUser(final String username, Map<String, Object> model, final Principal principal) {
		model.put("users", userService.searchForUsers(username));

		// get who the user is following, so that we can show the appropriate
		// follow/unfollow button
		String myUsername = principal.getName();
		List<String> following = userService.getFollowingList(myUsername);
		model.put("following", following);
		return "user_search_results";
	}
}
