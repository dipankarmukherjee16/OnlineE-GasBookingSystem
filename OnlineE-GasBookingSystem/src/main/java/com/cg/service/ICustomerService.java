package com.cg.service;

import com.cg.dto.CustomerDto;
import com.cg.exception.AadharAlreadyLinkedException;
import com.cg.exception.CustomerAlreadyExistException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.exception.ValidateException;

public interface ICustomerService {
	public Integer insertCustomer(CustomerDto customerdto) throws CylinderTypeMismatchException, CustomerAlreadyExistException;

	public boolean updateCustomer(CustomerDto customerdto, Integer customerId)
			throws CustomerNotFoundException, CylinderTypeMismatchException;

	public boolean linkAadhar(int custId, String aadharNo) throws CustomerNotFoundException, ValidateException, AadharAlreadyLinkedException;

}
