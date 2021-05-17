package com.cg.service;

import com.cg.dto.GasBookingDto;
import com.cg.entity.GasBooking;

public interface IGasBookingService {
	public Integer bookCylinder(GasBookingDto gasBookingdto);
	public boolean updateGasBooking(GasBookingDto gasBookingdto);
	public boolean deleteGasBooking(GasBookingDto gasBookingdto);
	public GasBooking generateInvoice(int customerId);
	public boolean updateDeliveryStatus(int dacNumber);
}