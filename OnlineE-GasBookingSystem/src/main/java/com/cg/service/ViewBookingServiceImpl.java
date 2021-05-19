package com.cg.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IGasBookingDao;
import com.cg.entity.GasBooking;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.GasBookingNotFoundException;
import com.cg.util.CgUtil;

@Service("viewbookingservice")
public class ViewBookingServiceImpl implements IViewBookingService {

	@Autowired
	private IGasBookingDao bookDao;
	@Override
	public String viewStatus(int gasBookingId) throws GasBookingNotFoundException {
		Optional<GasBooking> opbook = bookDao.findById(gasBookingId);
		if(!opbook.isPresent())
			throw new GasBookingNotFoundException(CgUtil.BOOKINGNOTFOUND);
	GasBooking book=opbook.get();
	
		return book.getStatus();
	
	
	}

	@Override
	public List<GasBooking> viewBookingDetails(int customerId)
			throws GasBookingNotFoundException {
		List<GasBooking> lst = bookDao.viewBookingDetailsByCustomerId(customerId);
		if (lst.isEmpty())
				throw new GasBookingNotFoundException(CgUtil.BOOKINGNOTFOUND);
		return lst;
	}

	@Override
	public Integer viewNoOfCylindersBooked(int year, int customerId) throws CustomerNotFoundException {
		List<GasBooking> lst = bookDao.noOfCylindersBookedInAYear(year, customerId);
		int noOfBookings=lst.size();
		return noOfBookings;
	}

}
