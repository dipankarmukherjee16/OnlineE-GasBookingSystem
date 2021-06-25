package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.GasBooking;
import com.cg.exception.GasBookingNotFoundException;
import com.cg.exception.NoGasBookingDoneException;
import com.cg.service.IViewBookingService;

/*********************************************************************************************
 *          @author: Moinak Majumder       
 *          @version: 1.0   
 *          Description: It is a controller class that provides the services for viewing status
 *          			 of booking, view booking details for given customer id, view number
 *          			 of cylinders booked in a given year                                   
 *          Created at: 20-MAY-2021
 **********************************************************************************************/

@RestController
public class ViewBookingRestController {

	@Autowired
	private IViewBookingService bookingService;
	
	
	/*********************************************************************************************
	 *          @author: Moinak Majumder       
	 *          @version: 1.0   
	 *          @return: List of GasBooking instance
	 *          @throws: GasBookingNotFoundException, if no booking detals for given customer id
	 *          Description: View gas booking details for given customer id                             
	 *          Created at: 20-MAY-2021
	 **********************************************************************************************/
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@GetMapping("viewbookingdetails/{customerid}")
	public List<GasBooking> viewBookingDetails(@PathVariable("customerid") int customerId)
			throws GasBookingNotFoundException {
		return bookingService.viewBookingDetails(customerId);

	}
	
	/*********************************************************************************************
	 *          @author: Enakshi Das        
	 *          @version: 1.0   
	 *          @return: String status
	 *          @throws: GasBookingNotFoundException, if booking not found for given booking id
	 *          Description: View status of a booking by booking id                          
	 *          Created at: 20-MAY-2021
	 **********************************************************************************************/

	@CrossOrigin(origins = {"http://localhost:4200"})
	@GetMapping("viewstatus/{gasbookingid}")
	public GasBooking viewStatus(@PathVariable("gasbookingid") int gasBookingId) throws GasBookingNotFoundException {
		return bookingService.viewStatus(gasBookingId);

	}
	
	/*********************************************************************************************
	 *          @author: Enakshi Das       
	 *          @version: 1.0   
	 *          @return: Integer noOfCylindersBooked
	 *          @throws: NoGasBookingDoneException, if no booking done by customer of given id
	 *          Description: Number of cylinders booked by the customer in a given year                            
	 *          Created at: 20-MAY-2021
	 **********************************************************************************************/

	@CrossOrigin(origins = {"http://localhost:4200"})
	@GetMapping("viewnoofcylindersbooked/{year}/{customerid}")
	public List<GasBooking> viewNoOfCylindersBooked(@PathVariable("year") int year, @PathVariable("customerid") int customerId)
			throws NoGasBookingDoneException {
		return bookingService.viewNoOfCylindersBooked(year, customerId);

	}
}
