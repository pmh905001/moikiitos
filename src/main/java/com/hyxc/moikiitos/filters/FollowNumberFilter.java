package com.hyxc.moikiitos.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hyxc.moikiitos.services.UserService;

public class FollowNumberFilter implements Filter {

	@Autowired
	private UserService userService;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			String username = authentication.getName();
			if (username != "anonymousUser") {
				request.setAttribute("followingNumber", userService.getFollowingList(username).size());
				request.setAttribute("followersNumber", userService.getFollowerUserList(username).size());
			}
		}

		filterChain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {

	}

}
