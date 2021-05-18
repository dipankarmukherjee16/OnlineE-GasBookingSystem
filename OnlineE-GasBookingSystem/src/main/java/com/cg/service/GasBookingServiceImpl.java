package com.cg.service;

import com.cg.dto.GasBookingDto;
import com.cg.entity.GasBooking;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.GasBookingNotFoundException;

public class GasBookingServiceImpl implements IGasBookingService{

	@Override
	public Integer bookCylinder(GasBookingDto gasBookingdto) throws CustomerNotFoundException {
		GasBooking book = new GasBooking();
		book.setBookingDate(gasBookingdto.getBookingDate());
		book.setStatus(gasBookingdto.getStatus());
		book.setBill(gasBookingdto.getBill());
		
		return null;
	}

	@Override
	public boolean cancelGasBooking(int bookingId) throws GasBookingNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GasBooking generateInvoice(int bookingId) throws GasBookingNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateDeliveryStatus(int dacNumber) throws GasBookingNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

}
