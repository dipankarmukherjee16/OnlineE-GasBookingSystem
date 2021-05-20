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

@Service("surrendercylinderservice")
@Transactional
public class SurrenderCylinderServiceImpl implements ISurrenderCylinderService {

	@Autowired
	private ISurrenderCylinderDao surrenderCylinderDao;

	@Autowired
	private ICustomerDao customerDao;

	@Override
	@Transactional
	public Integer surrenderCylinder(int customerId) throws CustomerNotFoundException {

		Optional<Customer> customer = null;
		customer = customerDao.findById(customerId);
		if (customer.isEmpty())
			throw new CustomerNotFoundException(CgUtil.CUSTOMERNOTFOUND);

		Customer cust=customer.get();
		SurrenderCylinder surrenderCylinder = new SurrenderCylinder();
		surrenderCylinder.setSurrenderDate(LocalDate.now());

		cust.setConnectionStatus(CgUtil.CONNECTIONSTATUSINACTIVE);
		surrenderCylinder.setCustomer(cust);
		SurrenderCylinder persistedSurrenderCylinder = surrenderCylinderDao.save(surrenderCylinder);

		return persistedSurrenderCylinder.getSurrenderId();
	}

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
