package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Customer;
import com.cg.exception.CityNotFoundException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.service.IViewCustomerService;

@RestController
public class ViewCustomerRestController {
	
	
	@Autowired
	private IViewCustomerService customerService;
	
	@GetMapping("viewallcustomer")
	public List<Customer> viewAllCustomers()throws CustomerNotFoundException{
		
		return customerService.viewAllCustomers();
		
	}
	
	@GetMapping("viewcustomerbyid/{customerid}")
	public Customer viewCustomerbyId(@PathVariable("customerid") int customerId)throws CustomerNotFoundException{
		return customerService.viewCustomerbyId(customerId);
	}
	
	@GetMapping("viewcustomerbycylindertype/{type}")
	public List<Customer> viewCustomerbyCylinderType(@PathVariable("type") String cylinderType)throws CustomerNotFoundException, CylinderTypeMismatchException{
		return customerService.viewCustomerbyCylinderType(cylinderType);
	}
	
	@GetMapping("viewcustomerbyarea/{city}")
	public List<Customer> viewCustomerbyArea(@PathVariable("city") String city)throws CustomerNotFoundException,CityNotFoundException{
		return customerService.viewCustomerbyArea(city);
	}
}
