package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.entity.GasBooking;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.GasBookingNotFoundException;

public interface IViewBookingService {
	public String viewStatus(int gasBookingId) throws GasBookingNotFoundException;
	public List<GasBooking> viewBookingDetails(int customerId) throws CustomerNotFoundException, GasBookingNotFoundException;
	public Integer viewNoOfCylindersBooked(LocalDate year, int customerId) throws CustomerNotFoundException;
}
