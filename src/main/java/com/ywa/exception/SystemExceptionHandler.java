package com.ywa.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SystemExceptionHandler {

	@ExceptionHandler({AccessDeniedException.class})
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public String handleUnauthorizedException(HttpServletRequest req, Exception ex) {
		return "Unauthorized : " + ex.getMessage();
	}
	
	@ExceptionHandler({BadCredentialsException.class})
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody
	public String handleCredentials(HttpServletRequest req, Exception ex) {
		return "Bad Credential" + ex.getMessage();
	}
}
