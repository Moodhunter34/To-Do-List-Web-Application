package com.qa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceExceptionHandlers {
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<String> userNotFoundExceptionHandler(UserNotFoundException unfe) {
		
		return new ResponseEntity<String>(unfe.getMessage(), HttpStatus.NOT_FOUND);
	}

}
