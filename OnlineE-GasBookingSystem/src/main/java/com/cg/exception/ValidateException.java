package com.cg.exception;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidateException extends Exception {

	private List<FieldError> errors;

	public ValidateException() {
		super();

	}

	public ValidateException(String message) {
		super(message);

	}

	public ValidateException(List<FieldError> errors) {
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}

}
