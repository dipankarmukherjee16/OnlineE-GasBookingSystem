package com.cg.service;

import com.cg.dto.CustomerDto;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.exception.ValidateException;

public interface ICustomerService {
	public Integer insertCustomer(CustomerDto customerdto) throws CylinderTypeMismatchException;

	public boolean updateCustomer(CustomerDto customerdto, Integer customerId)
			throws CustomerNotFoundException, CylinderTypeMismatchException;

	public boolean deleteCustomer(int custId) throws CustomerNotFoundException;

	public boolean linkAadhar(int custId, String aadharNo) throws CustomerNotFoundException, ValidateException;

}
