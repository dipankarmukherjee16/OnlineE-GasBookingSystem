package com.cg.service;

import java.util.List;

import com.cg.entity.Invoice;
import com.cg.exception.BookingLimitReachedException;
import com.cg.exception.CustomerInactiveException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.DuplicateInvoiceException;
import com.cg.exception.GasBookingNotFoundException;
import com.cg.exception.InvoiceException;

public interface IGasBookingService {
	public Integer bookCylinder(Integer customerId) throws CustomerNotFoundException,BookingLimitReachedException,CustomerInactiveException;

	public Invoice generateInvoice(Integer bookingId, Double fare) throws GasBookingNotFoundException, DuplicateInvoiceException;

	public List<Invoice> getInvoices() throws InvoiceException;

	public boolean cylinderDelivered(Integer invoiceId) throws InvoiceException;  
}
