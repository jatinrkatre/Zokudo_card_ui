/*
 * InstaRem Overseas Money Transfer.
 * https://www.instarem.com/en-in/
 *
 * Copyright (c) 2014-2019 InstaReM
 *
 * InstaRem is an acronym of Instant Remittance.
 * InstaRem Software is designed and developed to ease the Overseas Money Transfer.
 * It allows you to transfer your money overseas with minimum cost and time.
 *
 *
 * This file is licensed and cannot be accessed by outside InstaRem.
 * It can only be accessed and modified by the authorized InstaRem Technical Teams.
 * If any unauthorized, outside of the InstaRem, user found to be unlawfully modified
 * the content of this file,  will be prosecuted under the Copyright Act
 *
 */
package com.ui.product.zokudo.filter;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ui.product.zokudo.util.CommonUtil;
import com.ui.product.zokudo.util.Constants;
import com.ui.product.zokudo.util.SecurityUtil;
import com.ui.product.zokudo.util.UrlMetaData;

@Component
public class CustomFilter implements Filter {

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private UrlMetaData urlMetaData;

	public CustomFilter(final SecurityUtil securityUtil, final UrlMetaData urlMetaData) {
		this.securityUtil = securityUtil;
		this.urlMetaData = urlMetaData;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		setMandatorySessionFields(request, (request.getSession()));
		filterChain.doFilter(request, response);
	}

	private void setMandatorySessionFields(HttpServletRequest request, HttpSession session) {
		String roles = "";
//		if (SecurityContextHolder.getContext().getAuthentication() != null) {
//			Iterator itr = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator();
//			while (itr.hasNext()) {
//				roles += itr.next() + ",";
//			}
//			roles = roles.substring(0, (roles.length()) - 1);
//
//		}
		String userName = "";
		String password = "";
		if (request.getParameter("loginUsername") != null && !(request.getParameter("loginUsername").isEmpty())) {
			userName = request.getParameter("loginUsername");
			session.setAttribute("loginUsername", userName);
		}
		if (request.getParameter("loginPassword") != null && !(request.getParameter("loginPassword").isEmpty())) {
			password = request.getParameter("loginPassword");
			session.setAttribute("loginPassword", password);
		}
		generateLoggedInUserAuthorizationHeader(userName, password, session);

		session.setAttribute("clientCode", session.getAttribute("clientCode"));
		
		if (session.getAttribute("customLoginUrl") == null) {
			System.err.println("CLIENT_CODE - " + session.getAttribute("clientCode"));
			session.setAttribute("roles", roles);
	
		}
		session.setMaxInactiveInterval(30 * 60);

	}

	private void generateLoggedInUserAuthorizationHeader(String userName, String password, HttpSession session) {
		if (userName != null && !userName.isEmpty() && password != null && !password.isEmpty()) {
			session.setAttribute("authorizationHeader", securityUtil.getAuthorizationHeader(userName, password));
		}
	}

	@Override
	public void destroy() {
	}
}
