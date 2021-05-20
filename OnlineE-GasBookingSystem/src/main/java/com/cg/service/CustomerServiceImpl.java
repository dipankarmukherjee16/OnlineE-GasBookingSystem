package com.cg.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.ICustomerDao;
import com.cg.dao.ICylinderDao;
import com.cg.dto.CustomerDto;
import com.cg.entity.Customer;
import com.cg.entity.Cylinder;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CylinderNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.util.CgUtil;

@Service("customerservice")
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerDao custDao;

	@Autowired
	private ICylinderDao cylinderDao;

	@Override
	@Transactional
	public Integer insertCustomer(CustomerDto customerdto)
			throws CylinderTypeMismatchException, CylinderNotFoundException {
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
		return persistedCust.getCustomerId();
	}

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
		return true;
	}

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

	@Override
	@Transactional
	public boolean linkAadhar(int custId, String aadharNo) throws CustomerNotFoundException {
		Optional<Customer> optcust = custDao.findById(custId);
		if (!optcust.isPresent()) {
			throw new CustomerNotFoundException(CgUtil.CUSTOMERNOTFOUND);
		}

		Customer cust = optcust.get();
		cust.setAadharCard(aadharNo);
		custDao.save(cust);

		return true;
	}

}
