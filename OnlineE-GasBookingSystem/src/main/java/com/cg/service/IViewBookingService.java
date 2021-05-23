package com.cg.service;

import java.util.List;

import com.cg.entity.GasBooking;
import com.cg.exception.GasBookingNotFoundException;
import com.cg.exception.NoGasBookingDoneException;

public interface IViewBookingService {
	public String viewStatus(int gasBookingId) throws GasBookingNotFoundException;

	public List<GasBooking> viewBookingDetails(int customerId) throws GasBookingNotFoundException;

	public Integer viewNoOfCylindersBooked(int year, int customerId) throws NoGasBookingDoneException;
}
