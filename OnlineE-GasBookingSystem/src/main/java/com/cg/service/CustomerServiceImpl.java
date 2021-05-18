package com.cg.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.dao.ICustomerDao;
import com.cg.dao.ICylinderDao;
import com.cg.dao.IGasBookingDao;
import com.cg.dao.ISurrenderCylinderDao;
import com.cg.dto.CustomerDto;
import com.cg.dto.CylinderDto;
import com.cg.entity.Customer;
import com.cg.entity.Cylinder;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CylinderNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;

public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private ICustomerDao custDao;
	
	@Autowired
	private ICylinderDao cylinderDao;
	
	@Override
	@Transactional
	public Integer insertCustomer(CustomerDto customerdto) throws CylinderTypeMismatchException, CylinderNotFoundException {
		Customer cust= new Customer();
		cust.setUserName(customerdto.getUserName());
		cust.setMobileNumber(customerdto.getMobileNumber());
		cust.setEmail(customerdto.getEmail());
		cust.setAadharCard(customerdto.getAadharCard());
		cust.setAdderss(customerdto.getAdderss());
		cust.setCity(customerdto.getCity());
		cust.setConnectionStatus(customerdto.getConnectionStatus());
		
		Cylinder cylinder= null;
		cylinder=cylinderDao.findByCylinderType(customerdto.getCylinderType());
		if(cylinder== null) {
			throw new CylinderNotFoundException("No cylinder found");
		}
		cust.setCylinder(cylinder);
		Customer persistedCust= custDao.save(cust);
		return persistedCust.getCustomerId();
	}

	@Override
	public boolean updateCustomer(CustomerDto customerdto)
			throws CustomerNotFoundException, CylinderTypeMismatchException {
		Optional<Customer> optcust= custDao.findById(customerdto.getCustomerId());
		if(!optcust.isEmpty()) {
			throw new CustomerNotFoundException("No customer found");
		}
		Customer cust= new Customer();
		cust.setUserName(customerdto.getUserName());
		cust.setMobileNumber(customerdto.getMobileNumber());
		cust.setEmail(customerdto.getEmail());
		cust.setAadharCard(customerdto.getAadharCard());
		cust.setAdderss(customerdto.getAdderss());
		cust.setCity(customerdto.getCity());
		cust.setConnectionStatus(customerdto.getConnectionStatus());
		
		Cylinder cylinder= null;
		cylinder=cylinderDao.findByCylinderType(customerdto.getCylinderType());
		if(cylinder== null) {
			throw new CylinderTypeMismatchException("No cylinder found");
		}
		cust.setCylinder(cylinder);
		Customer persistedCust= custDao.save(cust);
		return true;
	}

	@Override
	public boolean deleteCustomer(int custId) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean linkAadhar(int custId, int aadharNo) throws CustomerNotFoundException {
		
		return false;
	}

}
