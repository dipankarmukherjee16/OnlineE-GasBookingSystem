package com.cg.service;

import java.util.List;

import com.cg.entity.Customer;
import com.cg.exception.CityNotFoundException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;

public interface IViewCustomerService {
	public List<Customer> viewAllCustomers() throws CustomerNotFoundException;

	public Customer viewCustomerbyId(int customerId) throws CustomerNotFoundException;

	public List<Customer> viewCustomerbyCylinderType(String cylinderType)
			throws CustomerNotFoundException, CylinderTypeMismatchException;

	public List<Customer> viewCustomerbyArea(String city) throws CityNotFoundException;

}
