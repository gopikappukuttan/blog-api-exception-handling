package com.edstem.blog_api_exception_handling.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationErrorResponse extends ErrorResponse{
		private Map<String, String> validationErrors;

		public ValidationErrorResponse(int status, String message, LocalDateTime timestamp, String path, Map<String, String> validationErrors) {
			super(status, message, timestamp, path);
			this.validationErrors = validationErrors;
		}

}
