package com.cg.exception;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidateUserException extends Exception{

	private final List<FieldError> errors;
	
	public ValidateUserException() {
		super();
		this.errors = null;

	}

	public ValidateUserException(String arg0) {
		super(arg0);
		this.errors = null;
	}
	
	public ValidateUserException(List<FieldError> errors) {
		this.errors=errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}
	

}
