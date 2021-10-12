package com.cst438.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
	/*
	 * used by React Login front end component to test if user is 
	 * logged in.  
	 *   response 401 indicates user is not logged in
	 *   a redirect response take user to Semester front end page.
	 */
	
	@Value("${frontend.post.login.url}")
	String redirect_url;
	
	
	@GetMapping("/user")
	public String user (@AuthenticationPrincipal OAuth2User principal){
		String name = principal.getAttribute("name");
		String email = principal.getAttribute("email");
		System.out.println("/user name = " + name + " : email = " + email);
		return "redirect:" + redirect_url;
	}
}