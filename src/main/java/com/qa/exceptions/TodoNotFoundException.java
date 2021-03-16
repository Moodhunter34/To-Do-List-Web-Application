package com.qa.exceptions;

import javax.persistence.EntityNotFoundException;

public class TodoNotFoundException extends EntityNotFoundException {

	public TodoNotFoundException() {
		super();
	}
	
	public TodoNotFoundException(String message) {
		super(message);
	}
}
