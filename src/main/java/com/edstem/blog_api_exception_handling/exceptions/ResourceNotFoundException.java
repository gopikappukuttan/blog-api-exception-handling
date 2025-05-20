package com.edstem.blog_api_exception_handling.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(String message){
		super(message);
	}
}
