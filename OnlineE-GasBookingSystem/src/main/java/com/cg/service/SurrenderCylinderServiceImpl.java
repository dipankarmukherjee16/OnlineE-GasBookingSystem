package com.cg.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.ICustomerDao;
import com.cg.dao.ISurrenderCylinderDao;
import com.cg.entity.Customer;
import com.cg.entity.SurrenderCylinder;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.SurrenderCylinderNotFoundException;
import com.cg.util.CgUtil;


/*********************************************************************************************
 *          @author          Debabrata Deb
 *          Description      It is a service class that provides the services for surrendering 
                             a connection, fetching all the surrendered customer details and 
                             viewing surrendered customer details for a given year           
 *         Version           1.0     
 *         Created Date      19-MAY-2021
 **********************************************************************************************/



@Service("surrendercylinderservice")
@Transactional
public class SurrenderCylinderServiceImpl implements ISurrenderCylinderService {

	@Autowired
	private ISurrenderCylinderDao surrenderCylinderDao;

	@Autowired
	private ICustomerDao customerDao;

	
	/************************************************************************************
	 * 	Method: surrenderCylinder
     * 	Description: To surrender a connection
     * 	Parameter customerId - Customers ID
	 * 	@returns Integer  - surrender cylinder id, if customer found otherwise throws CustomerNotFoundException
	 * 	@throws CustomerNotFoundException - when supplied customer id is not present
     *	Created By - Debabrata Deb
     *	Created Date - 19-MAY-2021                           
	
	 ************************************************************************************/

	
	
	@Override
	@Transactional
	public Integer surrenderCylinder(int customerId) throws CustomerNotFoundException {

		Optional<Customer> customer = null;
		customer = customerDao.findById(customerId);
		if (!customer.isPresent())
			throw new CustomerNotFoundException(CgUtil.CUSTOMERNOTFOUND);

		Customer cust=customer.get();
		SurrenderCylinder surrenderCylinder = new SurrenderCylinder();
		surrenderCylinder.setSurrenderDate(LocalDate.now());

		cust.setConnectionStatus(CgUtil.CONNECTIONSTATUSINACTIVE);
		surrenderCylinder.setCustomer(cust);
		SurrenderCylinder persistedSurrenderCylinder = surrenderCylinderDao.save(surrenderCylinder);

		return persistedSurrenderCylinder.getSurrenderId();
	}

	
	/************************************************************************************
	 * 	Method: viewAllSurrenderedCustomer
     * 	Description: To view all surrendered customers
	 * 	@returns List<Customer>  - all surrendered customers 
	 * 	@throws CustomerNotFoundException - when no surrendered customer is present  
     *	Created By - Debabrata Deb
     *	Created Date - 19-MAY-2021                           
	
	 ************************************************************************************/

	
	
	@Override
	public List<Customer> viewAllSurrenderedCustomer()
			throws CustomerNotFoundException, SurrenderCylinderNotFoundException {

		List<SurrenderCylinder> lst = surrenderCylinderDao.findAll();
		if (lst.isEmpty())
			throw new CustomerNotFoundException(CgUtil.CUSTOMERNOTFOUND);

		List<Customer> list = new ArrayList<>();
		for (SurrenderCylinder sc : lst)
			list.add(sc.getCustomer());

		return list;
	}

	
	/************************************************************************************
	 * 	Method: viewSurrenderedCustomer
     * 	Description: To view surrendered customers for a given year
     * 	Parameter year - a particular year
	 * 	@returns List<Customer>  - all surrendered customers for the given year  
	 * 	@throws CustomerNotFoundException - when no surrendered customer is present for given year
     *	Created By - Debabrata Deb
     *	Created Date - 19-MAY-2021                           
	
	 ************************************************************************************/
	
	
	
	@Override
	public List<Customer> viewSurrenderCustomer(int year) throws CustomerNotFoundException {

		List<SurrenderCylinder> lst = surrenderCylinderDao.viewSurrenderedCustomerByYear(year);
		if (lst.isEmpty())
			throw new CustomerNotFoundException(CgUtil.CUSTOMERNOTFOUND);

		List<Customer> list = new ArrayList<>();
		for (SurrenderCylinder sc : lst)
			list.add(sc.getCustomer());

		return list;
	}

}
