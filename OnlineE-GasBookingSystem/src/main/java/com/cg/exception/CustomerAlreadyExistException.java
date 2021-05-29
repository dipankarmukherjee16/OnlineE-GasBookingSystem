package com.cg.exception;

public class CustomerAlreadyExistException extends Exception{

	public CustomerAlreadyExistException() {
		super();
		
	}

	public CustomerAlreadyExistException(String message) {
		super(message);
	
	}

}
