package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Customer;
import com.cg.exception.CityNotFoundException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.service.IViewCustomerService;


/*********************************************************************************************
 *          @author: Dipankar Mukherjee        
 *          @version: 1.0   
 *          Description: It is a controller class that provides the services for viewing 
 *          			 details of all the customers, view details of a customer by id, 
 *          			 view details of customers by cylinder type and view details of 
 *          			 the customers by area                   
 *          Created at: 19-MAY-2021
 **********************************************************************************************/

@RestController
public class ViewCustomerRestController {

	@Autowired
	private IViewCustomerService customerService;


	/*********************************************************************************************
	 *          @author: Enakshi Das        
	 *          @version: 1.0   
	 *          @return: List<Customer>
	 *          @throws: CustomerNotFoundException, if no customer details added to the database
	 *          Description: View the details of all the customers in the database                           
	 *          Created at: 19-MAY-2021
	 **********************************************************************************************/

	@CrossOrigin
	@GetMapping("viewallcustomer")
	public List<Customer> viewAllCustomers() throws CustomerNotFoundException {

		return customerService.viewAllCustomers();

	}
	
	/*********************************************************************************************
	 *          @author: Enakshi Das       
	 *          @version: 1.0   
	 *          @return: Customer instance
	 *          @throws: CustomerNotFoundException, if customer id is wrong 
	 *          Description: View the details of a customer by ID                          
	 *          Created at: 19-MAY-2021
	 **********************************************************************************************/

	@CrossOrigin
	@GetMapping("viewcustomerbyid/{customerid}")
	public Customer viewCustomerbyId(@PathVariable("customerid") int customerId) throws CustomerNotFoundException {
		return customerService.viewCustomerbyId(customerId);
	}
	
	/*********************************************************************************************
	 *          @author: Dipankar Mukherjee        
	 *          @version: 1.0   
	 *          @return: List<Customer>
	 *          @throws: CylinderTypeMismatchException, if cylinder type does not match
	 *          		 CustomerNotFoundException, if customer id is wrong 
	 *          Description: View the details of customers by cylinder type                          
	 *          Created at: 19-MAY-2021
	 **********************************************************************************************/

	@CrossOrigin
	@GetMapping("viewcustomerbycylindertype/{type}")
	public List<Customer> viewCustomerbyCylinderType(@PathVariable("type") String cylinderType)
			throws CustomerNotFoundException, CylinderTypeMismatchException {
		return customerService.viewCustomerbyCylinderType(cylinderType);
	}


	/*********************************************************************************************
	 *          @author: Dipankar Mukherjee        
	 *          @version: 1.0   
	 *          @return: List<Customer>
	 *          @throws: CityNotFoundException, if city name entered wrong
	 *          Description: View the details of customers by area                         
	 *          Created at: 19-MAY-2021
	 **********************************************************************************************/
	
	@CrossOrigin
	@GetMapping("viewcustomerbyarea/{city}")
	public List<Customer> viewCustomerbyArea(@PathVariable("city") String city)
			throws CityNotFoundException {
		return customerService.viewCustomerbyArea(city);
	}
}
