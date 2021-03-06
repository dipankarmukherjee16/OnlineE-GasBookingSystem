package com.cg.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.cg.dto.ErrorMessage;
import com.cg.exception.BookingLimitReachedException;
import com.cg.exception.CustomerInactiveException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.DuplicateInvoiceException;
import com.cg.exception.GasBookingNotFoundException;
import com.cg.exception.InvoiceException;
import com.cg.exception.NoGasBookingDoneException;

@RestControllerAdvice
public class GasBookingAdvice {

	@ExceptionHandler(NoGasBookingDoneException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionNoGasBookingDone(NoGasBookingDoneException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionCustomerNotFound(CustomerNotFoundException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
	
	@ExceptionHandler(CustomerInactiveException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleExceptionCustomerInactive(CustomerInactiveException ex) {
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
	}

	@ExceptionHandler(GasBookingNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionGasBookingNotFound(GasBookingNotFoundException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
	
	@ExceptionHandler(BookingLimitReachedException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleExceptionBookingLimitReached(BookingLimitReachedException ex) {
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
	}

	@ExceptionHandler(InvoiceException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionInvoiceNotFound(InvoiceException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
	
	@ExceptionHandler(DuplicateInvoiceException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleExceptionDuplicateInvoice(DuplicateInvoiceException ex) {
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
	}


	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleExceptionForDate(MethodArgumentTypeMismatchException ex) {
		if (ex.getMessage().contains("Localdate"))
			return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Invalid Date Pattern");
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "It must be numeric");

	}

	@ExceptionHandler(HttpMessageConversionException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleException3(HttpMessageConversionException ex) {
		if (ex.getMessage().contains("LocalDate"))
			return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Invalid Date Pattern , follow yyyy-M-d");
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "It must be Numeric");
	}
}
