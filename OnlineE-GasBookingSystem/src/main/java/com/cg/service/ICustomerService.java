package com.cg.service;

import com.cg.dto.CustomerDto;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;

public interface ICustomerService {
	public Integer insertCustomer(CustomerDto customerdto)throws CylinderTypeMismatchException;
	public boolean updateCustomer(CustomerDto customerdto)throws CustomerNotFoundException, CylinderTypeMismatchException;
	public boolean deleteCustomer(int custId)throws CustomerNotFoundException;
	public boolean linkAadhar(int custId, int aadharNo)throws CustomerNotFoundException; 
	
	
}
