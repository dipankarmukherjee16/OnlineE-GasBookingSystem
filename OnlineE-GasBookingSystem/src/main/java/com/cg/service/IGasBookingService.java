package com.cg.service;

import java.util.List;

import com.cg.entity.Invoice;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.GasBookingNotFoundException;
import com.cg.exception.InvoiceException;

public interface IGasBookingService {
	public Integer bookCylinder(Integer customerId) throws CustomerNotFoundException;

	public boolean cancelGasBooking(Integer bookingId) throws GasBookingNotFoundException;

	public Invoice generateInvoice(Integer bookingId, Double fare) throws GasBookingNotFoundException;

	public List<Invoice> getInvoices() throws InvoiceException;

	public boolean cylinderDelivered(Integer invoiceId) throws InvoiceException;
}
