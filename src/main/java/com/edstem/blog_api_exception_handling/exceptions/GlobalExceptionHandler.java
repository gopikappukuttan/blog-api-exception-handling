package com.edstem.blog_api_exception_handling.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
		logger.warn("Resource not Found" + ex.getMessage());

		ErrorResponse response = new ErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				ex.getMessage(),
				LocalDateTime.now(),
				request.getRequestURI()
		);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
		Map<String, String> errors = new HashMap<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
		}

		logger.info("Validation failed: {}", errors);
		ValidationErrorResponse response = new ValidationErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				"Validation failed",
				LocalDateTime.now(),
				request.getRequestURI(),
				errors
		);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getConstraintViolations().forEach(cv ->
				errors.put(cv.getPropertyPath().toString(), cv.getMessage()));
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request) {
		logger.error("Database constraint violation: {}", ex.getMessage());
		ErrorResponse response = new ErrorResponse(
				HttpStatus.CONFLICT.value(),
				"Database error: " + ex.getRootCause().getMessage(),
				LocalDateTime.now(),
				request.getRequestURI()
		);
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<ErrorResponse> handleAuthorization(AuthorizationException ex, HttpServletRequest request) {
		logger.warn("Unauthorized access: {}", ex.getMessage());

		ErrorResponse response = new ErrorResponse(
				HttpStatus.FORBIDDEN.value(),
				ex.getMessage(),
				LocalDateTime.now(),
				request.getRequestURI()
		);
		return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, HttpServletRequest request) {
		logger.error("Unexpected error occurred", ex);

		ErrorResponse response = new ErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"An unexpected error occurred",
				LocalDateTime.now(),
				request.getRequestURI()
		);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
