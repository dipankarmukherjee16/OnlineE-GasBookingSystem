package com.cg.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.ICustomerDao;
import com.cg.dao.IGasBookingDao;
import com.cg.dto.GasBookingDto;
import com.cg.entity.Customer;
import com.cg.entity.GasBooking;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.GasBookingNotFoundException;
import com.cg.util.CgUtil;

@Service("gasbookingservice")
@Transactional
public class GasBookingServiceImpl implements IGasBookingService{
	
	@Autowired
	private IGasBookingDao bookDao;
	
	@Autowired
	private ICustomerDao custDao;

	@Override
	@Transactional
	public Integer bookCylinder(GasBookingDto gasBookingdto) throws CustomerNotFoundException {
		GasBooking book = new GasBooking();
		book.setBookingDate(gasBookingdto.getBookingDate());
		book.setStatus(gasBookingdto.getStatus());
		book.setBill(gasBookingdto.getBill());
		book.setDacNumber(gasBookingdto.getDacNumber());
		book.setDispatchDate(gasBookingdto.getDispatchDate());
		book.setCylinderId(gasBookingdto.getCylinderId());
		
		Optional<Customer> opcust = custDao.findById(gasBookingdto.getCustomerId());
		if(!opcust.isPresent())
			throw new CustomerNotFoundException(CgUtil.CUSTOMERNOTFOUND);
		Customer cust = opcust.get();
		book.setCustomer(cust);
		GasBooking persistedBook = bookDao.save(book);
		return persistedBook.getGasBookingId();
	}

	@Override
	@Transactional
	public boolean cancelGasBooking(int bookingId) throws GasBookingNotFoundException {
		Optional<GasBooking> opbook = bookDao.findById(bookingId);
		if(!opbook.isPresent())
			throw new GasBookingNotFoundException(CgUtil.BOOKINGNOTFOUND);
		GasBooking book = opbook.get();
		bookDao.delete(book);
		return true;
	}

	@Override
	@Transactional
	public GasBooking generateInvoice(int bookingId) throws GasBookingNotFoundException {
		Optional<GasBooking> opbook = bookDao.findById(bookingId);
		if(!opbook.isPresent())
			throw new GasBookingNotFoundException(CgUtil.BOOKINGNOTFOUND);
		GasBooking book = opbook.get();
		return book;
	}

	@Override
	public boolean updateDeliveryStatus(int dacNumber) throws GasBookingNotFoundException {
		
		return false;
	}

}
