package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.entity.GasBooking;

public interface IViewBookingService {
	public String viewStatus(int gasBookingId);
	public List<GasBooking> viewBookingDetails(int customerId);
	public Integer viewNoOfCylinders(LocalDate dispatchDate, int customerId);
}
