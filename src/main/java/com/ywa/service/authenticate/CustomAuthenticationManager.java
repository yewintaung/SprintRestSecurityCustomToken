package com.ywa.service.authenticate;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;

public class CustomAuthenticationManager extends DaoAuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) {
		try {
			Authentication auth = super.authenticate(authentication);
			return auth;
		} catch (BadCredentialsException e) {
			throw e;
		} catch (AuthenticationServiceException ae) {
			throw new BadCredentialsException("Email Not Found");
		}
	}
}
