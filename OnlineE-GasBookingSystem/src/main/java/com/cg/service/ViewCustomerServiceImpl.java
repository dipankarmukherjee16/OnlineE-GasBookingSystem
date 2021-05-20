package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.ICustomerDao;
import com.cg.dao.ICylinderDao;
import com.cg.entity.Customer;
import com.cg.entity.Cylinder;
import com.cg.exception.CityNotFoundException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.util.CgUtil;

@Service("customerviewser")
public class ViewCustomerServiceImpl implements IViewCustomerService {

	@Autowired
	private ICustomerDao custDao;
	
	@Autowired
	private ICylinderDao cylinderDao;

	@Override
	public List<Customer> viewAllCustomers() throws CustomerNotFoundException {
		List<Customer> lst = custDao.findAll();
		if (lst.isEmpty()) {
			throw new CustomerNotFoundException(CgUtil.NOCUSTOMERFOUND);
		}
		lst.sort((e1, e2) -> e1.getUserName().compareTo(e2.getUserName()));
		return lst;
	}

	@Override
	public Customer viewCustomerbyId(int customerId) throws CustomerNotFoundException {
		Optional<Customer> optcust = custDao.findById(customerId);
		if (!optcust.isPresent()) {
			throw new CustomerNotFoundException(CgUtil.CUSTOMERNOTFOUND);
		}
		return optcust.get();
	}

	@Override
	public List<Customer> viewCustomerbyCylinderType(String cylinderType)
			throws CustomerNotFoundException, CylinderTypeMismatchException {
		
		Cylinder cylinder = cylinderDao.findByCylinderType(cylinderType); 
		if(cylinder==null)
			throw new CylinderTypeMismatchException(CgUtil.CYLINDERTYPEMISMATCH);
		
		List<Customer> lst = custDao.viewCustomerByType(cylinderType);
		if (lst.isEmpty()) {
			throw new CustomerNotFoundException(CgUtil.NOCUSTOMERFOUND);
		}
		lst.sort((e1, e2) -> e1.getUserName().compareTo(e2.getUserName()));
		return lst;
	}

	@Override
	public List<Customer> viewCustomerbyArea(String city) throws CityNotFoundException {
		
		List<Customer> lst = custDao.viewCustomerByCity(city);
		if (lst.isEmpty()) {
			throw new CityNotFoundException(CgUtil.CITYNOTFOUND);
		}
		lst.sort((e1, e2) -> e1.getUserName().compareTo(e2.getUserName()));
		return lst;
	}

}
