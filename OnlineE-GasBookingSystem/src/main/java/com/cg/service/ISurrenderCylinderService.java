package com.cg.service;

import java.util.List;

import com.cg.entity.Customer;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.SurrenderCylinderNotFoundException;

public interface ISurrenderCylinderService {

	public Integer surrenderCylinder(int customerId) throws CustomerNotFoundException;

	public List<Customer> viewAllSurrenderedCustomer()
			throws CustomerNotFoundException, SurrenderCylinderNotFoundException;

	public List<Customer> viewSurrenderCustomer(int year) throws CustomerNotFoundException;

}
