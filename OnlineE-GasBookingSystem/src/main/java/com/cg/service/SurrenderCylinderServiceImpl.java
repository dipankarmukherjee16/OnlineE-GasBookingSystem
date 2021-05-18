package com.cg.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.ICustomerDao;
import com.cg.dao.ISurrenderCylinderDao;
import com.cg.dto.SurrenderCylinderDto;
import com.cg.entity.Customer;
import com.cg.entity.SurrenderCylinder;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.SurrenderCylinderNotFoundException;

@Service("surrendercylinderservice")
@Transactional
public class SurrenderCylinderServiceImpl implements ISurrenderCylinderService{

	@Autowired
	private ISurrenderCylinderDao surrenderCylinderDao;
	
	@Autowired
	private ICustomerDao customerDao;
	
	@Override
	@Transactional
	public Integer surrenderCylinder(SurrenderCylinderDto surrenderCylinderDto) throws CustomerNotFoundException {

		SurrenderCylinder surrenderCylinder=new SurrenderCylinder();
		surrenderCylinder.setSurrenderDate(surrenderCylinderDto.getSurrenderDate());
		
		Customer customer=null;
		customer=customerDao.findByCustomerId(surrenderCylinderDto.getCustomerId());
		if(customer==null)
			throw new CustomerNotFoundException("Customer not found");
		customer.setConnectionStatus("inactive");
		surrenderCylinder.setCustomer(customer);
		SurrenderCylinder persistedSurrenderCylinder=surrenderCylinderDao.save(surrenderCylinder);
		
		return persistedSurrenderCylinder.getSurrenderId();
	}

	@Override
	public List<Customer> viewAllSurrenderedCustomer()
			throws CustomerNotFoundException, SurrenderCylinderNotFoundException {
		
		List<SurrenderCylinder> lst=surrenderCylinderDao.findAll();
		if(lst.isEmpty())
			throw new CustomerNotFoundException("No customer found");
		
		List<Customer> list = new ArrayList<>();
		for(SurrenderCylinder sc: lst)
			list.add(sc.getCustomer());
		
		return list;
	}

	@Override
	public List<Customer> viewSurrenderCustomer(int year) throws CustomerNotFoundException {
		
		List<SurrenderCylinder> lst=surrenderCylinderDao.viewSurrenderedCustomerByYear(year);
		if(lst.isEmpty())
			throw new CustomerNotFoundException("No customer found");
		
		List<Customer> list = new ArrayList<>();
		for(SurrenderCylinder sc: lst)
			list.add(sc.getCustomer());
		
		return list;
	}

}
