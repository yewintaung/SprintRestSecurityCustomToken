package com.ywa.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

import com.ywa.service.authenticate.UserServiceImpl;


/**
 * 
 * @author Ye Kyaw Kyaw Htoo
 *
 */
public class TokenUtils {

	//public static final String MAGIC_KEY = "tqa3rpuc2"; //server
	
	public static final String MAGIC_KEY = "tqa3rpuc23";
	
	@Autowired
	UserServiceImpl userService;


	public static String createToken(UserDetails userDetails) {
		/* Expires in 30 mins */
		long expires = System.currentTimeMillis() + 1000L * 60 * 30;
		
		/* Expires in three Year */
		//long expiresOneYear = System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365 *3;
		
		StringBuilder tokenBuilder = new StringBuilder();
		tokenBuilder.append(userDetails.getUsername());
		tokenBuilder.append(":");
		// set expire time
		tokenBuilder.append(expires);
		
		tokenBuilder.append(":");
		
		tokenBuilder.append(TokenUtils.computeSignature(userDetails, expires));
		
		return tokenBuilder.toString();
	}


	public static String computeSignature(UserDetails userDetails, long expires) {
		
		if(userDetails == null) {
			return null;
		}
		
		StringBuilder signatureBuilder = new StringBuilder();
		signatureBuilder.append(userDetails.getUsername());
		signatureBuilder.append(":");
		signatureBuilder.append(expires);
		signatureBuilder.append(":");
		signatureBuilder.append(userDetails.getPassword());
		signatureBuilder.append(":");
		signatureBuilder.append(TokenUtils.MAGIC_KEY);

		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("No MD5 algorithm available!");
		}

		return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
	}
	
	public static String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
		/* Get token from header */
		String authToken = httpRequest.getHeader("X-Auth-Token");

		/* If token not found get it from request parameter */
		if (authToken == null) {
			authToken = httpRequest.getParameter("token");
		}

		return authToken;
	}

	public static String getUserNameFromToken(String authToken) {
		if (null == authToken) {
			return null;
		}

		String[] parts = authToken.split(":");
		return parts[0];
	}


	public static boolean validateToken(String authToken, UserDetails userDetails) {

		String[] parts ;
		String signature;
		long expires;
		
		// validate here
		

		try {
			parts = authToken.split(":");
			expires = Long.parseLong(parts[1]);
			signature = parts[2];

			if (expires < System.currentTimeMillis()) {
				return false;
			}

		} catch (Exception e) {
			return false;
		}

		return signature.equals(TokenUtils.computeSignature(userDetails, expires));
	}
	
}