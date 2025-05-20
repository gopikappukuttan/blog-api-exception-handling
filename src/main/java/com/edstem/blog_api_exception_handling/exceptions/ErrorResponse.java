package com.edstem.blog_api_exception_handling.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	private int status;
	private String message;
	private LocalDateTime timestamp;
	private String path;
}
