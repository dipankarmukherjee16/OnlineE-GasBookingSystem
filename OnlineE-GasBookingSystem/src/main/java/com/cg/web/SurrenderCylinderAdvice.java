package com.cg.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.cg.dto.ErrorMessage;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.SurrenderCylinderNotFoundException;
import com.cg.exception.ValidateException;


/*********************************************************************************************
 *          @author          Debabrata Deb          
 *          Description      It is a global exception handler class that provides the exceptions 
           				     for surrendering a connection, fetching all the surrendered customer 
           				     details and viewing surrendered customer details for a given year                           
 *         Version           1.0
 *         Created Date      20-MAY-2021
 **********************************************************************************************/



@RestControllerAdvice
public class SurrenderCylinderAdvice {

	
	/************************************************************************************
	 * 	Method: handleExceptionCustomerNotFound
     * 	Description: To handle CustomerNotFound exception 
	 * 	@returns ErrorMessage    
     *	Created By - Debabrata Deb
     *	Created Date - 20-MAY-2021                           
	
	 ************************************************************************************/

	
	
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionCustomerNotFound(CustomerNotFoundException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}

	
	/************************************************************************************
	 * 	Method: handleExceptionSurrenderCylinderNotFound
     * 	Description: To handle SurrenderCylinderNotFound exception 
	 * 	@returns ErrorMessage    
     *	Created By - Debabrata Deb
     *	Created Date - 20-MAY-2021                           
	
	 ************************************************************************************/

	
	
	@ExceptionHandler(SurrenderCylinderNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionSurrenderCylinderNotFound(SurrenderCylinderNotFoundException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
	
	
	/************************************************************************************
	 * 	Method: handleExceptionForDate
     * 	Description: To handle invalid date pattern 
	 * 	@returns ErrorMessage    
     *	Created By - Debabrata Deb
     *	Created Date - 20-MAY-2021                           
	
	 ************************************************************************************/

	

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleExceptionForDate(MethodArgumentTypeMismatchException ex) {
		if (ex.getMessage().contains("Localdate"))
			return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Invalid Date Pattern");
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "It must be numeric");

	}
	
	
	/************************************************************************************
	 * 	Method: handleException2
     * 	Description: To handle validation exception 
	 * 	@returns ErrorMessage    
     *	Created By - Debabrata Deb
     *	Created Date - 20-MAY-2021                           
	
	 ************************************************************************************/

	

	@ExceptionHandler(ValidateException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleException2(ValidateException ex) {
		List<String> errors = ex.getErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), errors);
	}

	
	/************************************************************************************
	 * 	Method: handleException3
     * 	Description: To handle HttpMessageConversion exception 
	 * 	@returns ErrorMessage    
     *	Created By - Debabrata Deb
     *	Created Date - 20-MAY-2021                           
	
	 ************************************************************************************/

	
	
	@ExceptionHandler(HttpMessageConversionException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleException3(HttpMessageConversionException ex) {
		if (ex.getMessage().contains("LocalDate"))
			return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Invalid Date Pattern , follow yyyy-MM-dd");
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "It must be Numeric");
	}

}
