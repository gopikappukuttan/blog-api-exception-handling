package com.edstem.blog_api_exception_handling.exceptions;

public class AuthorizationException extends RuntimeException{
	public AuthorizationException(String message) {
		super(message);
	}
}
