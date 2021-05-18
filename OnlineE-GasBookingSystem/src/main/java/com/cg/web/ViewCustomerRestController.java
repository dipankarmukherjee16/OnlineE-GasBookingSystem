package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Customer;
import com.cg.exception.CustomerNotFoundException;
import com.cg.service.IViewCustomerService;

@RestController
public class ViewCustomerRestController {
	
	
	@Autowired
	private IViewCustomerService customerService;
	
	@GetMapping("viewallcustomer")
	public List<Customer> viewAllCustomers()throws CustomerNotFoundException{
		
		return customerService.viewAllCustomers();
		
	}
	
}
