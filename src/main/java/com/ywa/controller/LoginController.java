package com.ywa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ywa.helper.RoleConstant;
import com.ywa.helper.TokenUtils;
import com.ywa.service.authenticate.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	AuthenticationService authenticateService;
	
	@Autowired
	UserDetailsService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, 
			HttpServletRequest request) {
		
		authenticateService.authenticate(email, password);
		
		UserDetails userDetails = userService.loadUserByUsername(email);
		
		return TokenUtils.createToken(userDetails);
	}
	
	@Secured({RoleConstant.ROLE_ADMIN, RoleConstant.ROLE_USER})
	@RequestMapping(value = "/renew", method = RequestMethod.GET)
	public String renewToken(HttpServletRequest request) {
		
		String token = TokenUtils.extractAuthTokenFromRequest(request);
		String email = TokenUtils.getUserNameFromToken(token);
		
		UserDetails userDetails = userService.loadUserByUsername(email);
		
		return TokenUtils.createToken(userDetails);
	}
	
}
