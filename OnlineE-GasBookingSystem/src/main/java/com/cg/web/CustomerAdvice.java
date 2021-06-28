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
import com.cg.exception.AadharAlreadyLinkedException;
import com.cg.exception.CityNotFoundException;
import com.cg.exception.CustomerAlreadyExistException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CylinderNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.exception.ValidateException;


/*********************************************************************************************
 *          @author: Dipankar Mukherjee        
 *          @version: 1.0   
 *          Description: It is a controller advice class that provides the global exception
 *          			 for handling exception for CustomerRestController and 
 *          			 ViewCustomerRestController                                  
 *          Created at: 20-MAY-2021
 **********************************************************************************************/

@RestControllerAdvice
public class CustomerAdvice {
	
	/************************************************************************************
	 * @author: Dipankar Mukherjee
	 * @version: 1.0
	 * @returns ErrorMessage 
	 * Description: To handle CustomerNotFoundException
	 * Created Date - 20-MAY-2021
	 ************************************************************************************/

	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionCustomerNotFound(CustomerNotFoundException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
	
	
	@ExceptionHandler(CustomerAlreadyExistException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleExceptionCustomerAlreadyExist(CustomerAlreadyExistException ex) {
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
	}
	
	@ExceptionHandler(AadharAlreadyLinkedException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleExceptionAadharAlreadyLinked(AadharAlreadyLinkedException ex) {
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
	}
	
	@ExceptionHandler(CityNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionCityNotFound(CityNotFoundException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
	
	/************************************************************************************
	 * @author: Dipankar Mukherjee
	 * @version: 1.0
	 * @returns ErrorMessage 
	 * Description: To handle CylinderTypeMismatchException 
	 * Created Date - 20-MAY-2021
	 ************************************************************************************/

	@ExceptionHandler(CylinderTypeMismatchException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionCylinderTypeMismatch(CylinderTypeMismatchException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
	
	/************************************************************************************
	 * @author: Dipankar Mukherjee
	 * @version: 1.0
	 * @returns ErrorMessage 
	 * Description: To handle CylinderNotFoundException 
	 * Created Date - 20-MAY-2021
	 ************************************************************************************/

	@ExceptionHandler(CylinderNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionCylinderNotFound(CylinderNotFoundException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}

	/************************************************************************************
	 * @author: Dipankar Mukherjee
	 * @version: 1.0
	 * @returns ErrorMessage 
	 * Description: To handle invalid date pattern 
	 * Created Date - 20-MAY-2021
	 ************************************************************************************/
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleExceptionForDate(MethodArgumentTypeMismatchException ex) {
		if (ex!=null && ex.getMessage().contains("Localdate"))
			return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Invalid Date Pattern");
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "It must be numeric");

	}
	
	/************************************************************************************
	 * @author: Dipankar Mukherjee
	 * @version: 1.0 
	 * @returns ErrorMessage 
	 * Description: To handle validation exception
	 * Created Date - 20-MAY-2021
	 ************************************************************************************/

	@ExceptionHandler(ValidateException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleException2(ValidateException ex) {
		List<String> errors = ex.getErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), errors);
	}

	/************************************************************************************
	 * @author: Dipankar Mukherjee
	 * @version: 1.0
	 * @returns ErrorMessage 
	 * Description: To handle HttpMessageConversion exception
	 * Created Date - 20-MAY-2021
	 ************************************************************************************/

	@ExceptionHandler(HttpMessageConversionException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleException3(HttpMessageConversionException ex) {
		if (ex.getMessage()!=null &&  ex.getMessage().contains("LocalDate"))
		
			return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Invalid Date Pattern , follow yyyy-MM-dd");
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "It must be Numeric");
	}

}
