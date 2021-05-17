package com.cg.service;

import com.cg.dto.GasBookingDto;
import com.cg.entity.GasBooking;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.GasBookingNotFoundException;

public interface IGasBookingService {
	public Integer bookCylinder(GasBookingDto gasBookingdto) throws CustomerNotFoundException;
	public boolean cancelGasBooking(int bookingId) throws GasBookingNotFoundException;
	public GasBooking generateInvoice(int bookingId) throws GasBookingNotFoundException;
	public boolean updateDeliveryStatus(int dacNumber) throws GasBookingNotFoundException;
}