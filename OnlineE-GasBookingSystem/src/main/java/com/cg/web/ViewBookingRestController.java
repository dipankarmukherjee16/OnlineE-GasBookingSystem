package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.GasBooking;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.GasBookingNotFoundException;
import com.cg.service.IViewBookingService;

@RestController
public class ViewBookingRestController {
	
	@Autowired
	private IViewBookingService bookingService;
	
	
	@GetMapping("viewstatus/{gasbookingid}")
	public String viewStatus(@PathVariable("gasbookingid") int gasBookingId) throws GasBookingNotFoundException{
		return bookingService.viewStatus(gasBookingId);
		
	}
	
	@GetMapping("viewbookingdetails/{customerid}")
	public List<GasBooking> viewBookingDetails(@PathVariable("customerid") int customerId) throws GasBookingNotFoundException{
		return bookingService.viewBookingDetails(customerId);
		
	}
	
	@GetMapping("viewnoofcylindersbooked/{year}/{customerid}")
	public Integer viewNoOfCylindersBooked(@PathVariable("year") int year,@PathVariable("customerid") int customerId) throws CustomerNotFoundException{
		return bookingService.viewNoOfCylindersBooked(year, customerId);
		
	}
}
