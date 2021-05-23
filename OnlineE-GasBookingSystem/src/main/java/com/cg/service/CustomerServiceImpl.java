package com.cg.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.ICustomerDao;
import com.cg.dao.ICylinderDao;
import com.cg.dto.CustomerDto;
import com.cg.entity.Customer;
import com.cg.entity.Cylinder;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.exception.ValidateException;
import com.cg.util.CgUtil;

/*********************************************************************************************
 *          @author: Dipankar Mukherjee        
 *          @version: 1.0   
 *          Description: It is a service class that provides the services for adding 
                         a new customer, update an existing customer, delete an existing
                         customer and link aadhar of an existing customer                                   
 *          Created at: 18-MAY-2021
 **********************************************************************************************/


@Service("customerservice")
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerDao custDao;

	@Autowired
	private ICylinderDao cylinderDao;
	
	Logger logger= LoggerFactory.getLogger(CustomerServiceImpl.class);

	/*********************************************************************************************
	 *          @author: Dipankar Mukherjee        
	 *          @version: 1.0   
	 *          @return: customerId
	 *          @throws: CylinderTypeMismatchException, if cylinder type does not match
	 *          Description: Insert new customer with details into the database                             
	 *          Created at: 18-MAY-2021
	 **********************************************************************************************/

	
	@Override
	@Transactional
	public Integer insertCustomer(CustomerDto customerdto) throws CylinderTypeMismatchException {
		Customer cust = new Customer();
		Cylinder cylinder = null;
		cylinder = cylinderDao.findByCylinderType(customerdto.getCylinderType());
		if (cylinder == null) {
			throw new CylinderTypeMismatchException(CgUtil.CYLINDERTYPEMISMATCH);
		}
		cust.setUserName(customerdto.getUserName());
		cust.setMobileNumber(customerdto.getMobileNumber());
		cust.setEmail(customerdto.getEmail());
		cust.setAadharCard(customerdto.getAadharCard());
		cust.setAddress(customerdto.getAddress());
		cust.setCity(customerdto.getCity());
		cust.setConnectionStatus(CgUtil.CONNECTION_ACTIVE);
		
		cust.setCylinder(cylinder);
		Customer persistedCust = custDao.save(cust);
		logger.info(CgUtil.CUSTOMER_CREATED);
		return persistedCust.getCustomerId();
	}


	/*********************************************************************************************
	 *          @author: Dipankar Mukherjee        
	 *          @version: 1.0   
	 *          @return: true
	 *          @throws: CylinderTypeMismatchException, if cylinder type does not match
	 *          		 CustomerNotFoundException, if customer id is wrong 
	 *          Description: Insert new customer with details into the database                            
	 *          Created at: 18-MAY-2021
	 **********************************************************************************************/

	@Override
	@Transactional
	public boolean updateCustomer(CustomerDto customerdto, Integer customerId)
			throws CustomerNotFoundException, CylinderTypeMismatchException {
		Optional<Customer> optcust = custDao.findById(customerId);
		if (!optcust.isPresent()) {
			throw new CustomerNotFoundException(CgUtil.CUSTOMERNOTFOUND);
		}
		Customer cust = optcust.get();
		cust.setUserName(customerdto.getUserName());
		cust.setMobileNumber(customerdto.getMobileNumber());
		cust.setEmail(customerdto.getEmail());
		cust.setAadharCard(customerdto.getAadharCard());
		cust.setAddress(customerdto.getAddress());
		cust.setCity(customerdto.getCity());

		Cylinder cylinder = null;
		cylinder = cylinderDao.findByCylinderType(customerdto.getCylinderType());
		if (cylinder == null) {
			throw new CylinderTypeMismatchException(CgUtil.CYLINDERTYPEMISMATCH);
		}
		cust.setCylinder(cylinder);
		custDao.save(cust);
		logger.info(CgUtil.CUSTOMER_UPDATED);
		return true;
	}

	/*********************************************************************************************
	 *          @author: Dipankar Mukherjee        
	 *          @version: 1.0   
	 *          @return: true
	 *          @throws: CustomerNotFoundException, if customer id is wrong    
	 *          Description: Delete details of an existing customer                         
	 *          Created at: 18-MAY-2021
	 **********************************************************************************************/

	
	@Override
	@Transactional
	public boolean deleteCustomer(int custId) throws CustomerNotFoundException {
		Optional<Customer> optcust = custDao.findById(custId);
		if (!optcust.isPresent()) {
			throw new CustomerNotFoundException(CgUtil.CUSTOMERNOTFOUND);
		}
		Customer cust = optcust.get();
		custDao.delete(cust);
		return true;
	}

	/*********************************************************************************************
	 *          @author: Dipankar Mukherjee        
	 *          @version: 1.0   
	 *          @return: true
	 * @throws ValidateException 
	 *          @throws: CustomerNotFoundException, if customer id is wrong          
	 *          Description: Link aadhar number to an existing customer details                   
	 *          Created at: 18-MAY-2021
	 **********************************************************************************************/

	
	@Override
	@Transactional
	public boolean linkAadhar(int custId, String aadharNo) throws CustomerNotFoundException, ValidateException {
		Optional<Customer> optcust = custDao.findById(custId);
		if (!optcust.isPresent()) {
			throw new CustomerNotFoundException(CgUtil.CUSTOMERNOTFOUND);
		}
		if(aadharNo==null || !aadharNo.matches("[0-9]{12}")) {
			throw new ValidateException(CgUtil.AADHAR_PATTERN);
		}
		Customer cust = optcust.get();
		cust.setAadharCard(aadharNo);
		custDao.save(cust);
		logger.info(CgUtil.AADHAR_LINKED);
		return true;
	}

}
