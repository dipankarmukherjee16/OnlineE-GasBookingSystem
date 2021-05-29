package com.cg.exception;

public class BookingLimitReachedException extends Exception{

	public BookingLimitReachedException() {
		super();
		
	}

	public BookingLimitReachedException(String message) {
		super(message);
		
	}
	
}
