package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IGasBookingDao;
import com.cg.entity.GasBooking;
import com.cg.exception.GasBookingNotFoundException;
import com.cg.exception.NoGasBookingDoneException;
import com.cg.util.CgUtil;

/*********************************************************************************************
 *          @author: Moinak Majumder        
 *          @version: 1.0   
 *          Description: It is a service class that provides the services for viewing status
 *          			 of booking, view booking details for given customer id, view number
 *          			 of cylinders booked in a given year                           
 *          Created at: 19-MAY-2021
 **********************************************************************************************/

@Service("viewbookingservice")
public class ViewBookingServiceImpl implements IViewBookingService {

	@Autowired
	private IGasBookingDao bookDao;
	
	/*********************************************************************************************
	 *          @author: Enakshi Das       
	 *          @version: 1.0   
	 *          @return: String status
	 *          @throws: GasBookingNotFoundException, if booking not found for given booking id
	 *          Description: View status of a booking by booking id                             
	 *          Created at: 19-MAY-2021
	 **********************************************************************************************/
	
	@Override
	public String viewStatus(int gasBookingId) throws GasBookingNotFoundException {
		Optional<GasBooking> opbook = bookDao.findById(gasBookingId);
		if (!opbook.isPresent())
			throw new GasBookingNotFoundException(CgUtil.BOOKINGNOTFOUND);
		GasBooking book = opbook.get();

		return book.getStatus();

	}
	

	/*********************************************************************************************
	 *          @author: Moinak Majumder       
	 *          @version: 1.0   
	 *          @return: List of GasBooking instance
	 *          @throws: GasBookingNotFoundException, if no booking detals for given customer id
	 *          Description: View gas booking details for given customer id                             
	 *          Created at: 19-MAY-2021
	 **********************************************************************************************/

	@Override
	public List<GasBooking> viewBookingDetails(int customerId) throws GasBookingNotFoundException {
		List<GasBooking> lst = bookDao.viewBookingDetailsByCustomerId(customerId);
		if (lst.isEmpty())
			throw new GasBookingNotFoundException(CgUtil.BOOKINGNOTFOUND);
		return lst;
	}
	

	/*********************************************************************************************
	 *          @author: Enakshi Das       
	 *          @version: 1.0   
	 *          @return: Integer noOfCylindersBooked
	 *          @throws: NoGasBookingDoneException, if no booking done by customer of given id
	 *          Description: Number of cylinders booked by the customer in a given year                            
	 *          Created at: 19-MAY-2021
	 **********************************************************************************************/

	@Override
	public Integer viewNoOfCylindersBooked(int year, int customerId) throws NoGasBookingDoneException {
		List<GasBooking> lst = bookDao.noOfCylindersBookedInAYear(year, customerId);
		if (lst.isEmpty())
			throw new NoGasBookingDoneException(CgUtil.NOBOOKINGDONE);
		return lst.size();
	}

}
