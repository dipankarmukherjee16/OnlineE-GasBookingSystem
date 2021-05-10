package com.cg.repository;

import com.cg.entity.GasBooking;
import com.cg.exception.GasBookingNotFoundException;

public interface IGasBookingRepository {
	public GasBooking insertGasBooking(GasBooking gasBooking);
	public GasBooking updateGasBooking(GasBooking gasBooking) throws GasBookingNotFoundException;
	public GasBooking deleteGasBooking(GasBooking gasBooking) throws GasBookingNotFoundException;
	public GasBooking calculateBill(int customerId)throws GasBookingNotFoundException;
}