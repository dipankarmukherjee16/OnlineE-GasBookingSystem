package com.cg.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.CustomerDto;
import com.cg.dto.SuccessMessage;
import com.cg.exception.CustomerAlreadyExistException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.exception.ValidateException;
import com.cg.service.ICustomerService;
import com.cg.util.CgUtil;


/*********************************************************************************************
 *          @author: Dipankar Mukherjee        
 *          @version: 1.0   
 *          Description: It is a controller class that provides the services for adding 
                         a new customer, update an existing customer, delete an existing
                         customer and link aadhar of an existing customer                                   
 *          Created at: 19-MAY-2021
 **********************************************************************************************/

@RestController
public class CustomerRestController {

	@Autowired
	private ICustomerService customerService;


	/*********************************************************************************************
	 *          @author: Dipankar Mukherjee        
	 *          @version: 1.0   
	 *          @return: SuccessMessage
	 * @throws CustomerAlreadyExistException 
	 *          @throws: CylinderTypeMismatchException, if cylinder type does not match
	 *          		 ValidateException, if invalid details entered
	 *          Description: Insert new customer with details into the database                             
	 *          Created at: 19-MAY-2021
	 **********************************************************************************************/

	@PostMapping("addcustomer")
	public SuccessMessage addCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult br)
			throws ValidateException, CylinderTypeMismatchException, CustomerAlreadyExistException {
		if (br.hasErrors()) {
			throw new ValidateException(br.getFieldErrors());
		}
		int custId = customerService.insertCustomer(customerDto);
		return new SuccessMessage(CgUtil.CUSTOMER_CREATED + custId);

	}


	/*********************************************************************************************
	 *          @author: Dipankar Mukherjee        
	 *          @version: 1.0   
	 *          @return: SuccessMessage
	 *          @throws: CylinderTypeMismatchException, if cylinder type does not match
	 *          		 CustomerNotFoundException, if customer id is wrong 
	 *          		 ValidateException, if invalid details entered
	 *          Description: Update new customer with details into the database                            
	 *          Created at: 19-MAY-2021
	 **********************************************************************************************/

	@PutMapping("editcustomer/{customerid}")
	public SuccessMessage editCustomer(@Valid @RequestBody CustomerDto customerDto,
			@PathVariable("customerid") int customerId, BindingResult br)
			throws ValidateException, CustomerNotFoundException, CylinderTypeMismatchException {
		if (br.hasErrors()) {
			throw new ValidateException(br.getFieldErrors());
		}
		customerService.updateCustomer(customerDto, customerId);
		return new SuccessMessage(CgUtil.CUSTOMER_UPDATED);
	}

	/*********************************************************************************************
	 *          @author: Dipankar Mukherjee        
	 *          @version: 1.0   
	 *          @return: SuccessMessage
	 * @throws ValidateException 
	 *          @throws: CustomerNotFoundException, if customer id is wrong          
	 *          Description: Link aadhar number to an existing customer details                   
	 *          Created at: 19-MAY-2021
	 **********************************************************************************************/



	@PutMapping("linkaadhar/{customerid}/{aadharno}")
	public SuccessMessage linkAadhar(@PathVariable("customerid") int customerId,
			@PathVariable("aadharno") String aadharno) throws CustomerNotFoundException, ValidateException {
		customerService.linkAadhar(customerId, aadharno);
		return new SuccessMessage(CgUtil.AADHAR_LINKED);
	}

}
