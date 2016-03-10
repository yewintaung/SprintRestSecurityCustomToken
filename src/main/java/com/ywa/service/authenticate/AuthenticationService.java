package com.ywa.service.authenticate;

import org.springframework.security.authentication.BadCredentialsException;

public interface AuthenticationService {

	public void authenticate(String email, String password) throws BadCredentialsException;
}
