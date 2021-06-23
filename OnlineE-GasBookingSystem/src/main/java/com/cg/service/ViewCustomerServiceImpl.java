package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cg.dao.ICustomerDao;
import com.cg.dao.ICylinderDao;
import com.cg.entity.Customer;
import com.cg.entity.Cylinder;
import com.cg.exception.CityNotFoundException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.util.CgUtil;

/*********************************************************************************************
 *          @author: Dipankar Mukherjee        
 *          @version: 1.0   
 *          Description: It is a service class that provides the services for viewing 
 *          			 details of all the customers, view details of a customer by id, 
 *          			 view details of customers by cylinder type and view details of 
 *          			 the customers by area                   
 *          Created at: 17-MAY-2021
 **********************************************************************************************/

@Service("customerviewser")
public class ViewCustomerServiceImpl implements IViewCustomerService {

	@Autowired
	private ICustomerDao custDao;
	
	@Autowired
	private ICylinderDao cylinderDao;

	/*********************************************************************************************
	 *          @author: Enakshi Das         
	 *          @version: 1.0   
	 *          @return: List<Customer>
	 *          @throws: CustomerNotFoundException, if no customer details added to the database
	 *          Description: View the details of all the customers in the database                           
	 *          Created at: 17-MAY-2021
	 **********************************************************************************************/

	@Override
	public List<Customer> viewAllCustomers() throws CustomerNotFoundException {
		List<Customer> lst = custDao.findAll();
		if (lst.isEmpty()) {
			throw new CustomerNotFoundException(CgUtil.NOCUSTOMERFOUND);
		}
		lst.sort((e1, e2) -> e1.getUserName().compareTo(e2.getUserName()));
		return lst;
	}
	
	/*********************************************************************************************
	 *          @author: Enakshi Das         
	 *          @version: 1.0   
	 *          @return: Customer instance
	 *          @throws: CustomerNotFoundException, if customer id is wrong 
	 *          Description: View the details of a customer by ID                          
	 *          Created at: 17-MAY-2021
	 **********************************************************************************************/


	@Override
	public Customer viewCustomerbyId(int customerId) throws CustomerNotFoundException {
		Optional<Customer> optcust = custDao.findById(customerId);
		if (!optcust.isPresent()) {
			throw new CustomerNotFoundException(CgUtil.CUSTOMERNOTFOUND);
		}
		return optcust.get();
	}

	/*********************************************************************************************
	 *          @author: Dipankar Mukherjee        
	 *          @version: 1.0   
	 *          @return: List<Customer>
	 *          @throws: CylinderTypeMismatchException, if cylinder type does not match
	 *          		 CustomerNotFoundException, if customer id is wrong 
	 *          Description: View the details of customers by cylinder type                          
	 *          Created at: 17-MAY-2021
	 **********************************************************************************************/

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

	/*********************************************************************************************
	 *          @author: Dipankar Mukherjee        
	 *          @version: 1.0   
	 *          @return: List<Customer>
	 *          @throws: CityNotFoundException, if city name entered wrong
	 *          Description: View the details of customers by area                         
	 *          Created at: 17-MAY-2021
	 **********************************************************************************************/
	
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
