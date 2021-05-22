package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.SuccessMessage;
import com.cg.entity.Invoice;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.GasBookingNotFoundException;
import com.cg.exception.InvoiceException;
import com.cg.service.IGasBookingService;
import com.cg.util.CgUtil;

@RestController
public class BookingRestController {

	@Autowired
	private IGasBookingService gasBookingService;

	@PostMapping("bookcylinder/{customerid}")
	public SuccessMessage bookCylinder(@PathVariable("customerid") int customerId) throws CustomerNotFoundException {
		int gasBookingId = gasBookingService.bookCylinder(customerId);
		return new SuccessMessage(CgUtil.CYLINDER_BOOKED + gasBookingId);
	}

	@PostMapping("generateinvoice/{bookingid}/{fare}")
	public SuccessMessage generateInvoice(@PathVariable("bookingid") int bookingId, @PathVariable("fare") double fare)
			throws GasBookingNotFoundException {
		gasBookingService.generateInvoice(bookingId, fare);
		return new SuccessMessage(CgUtil.INVOICE_GENERATED);
	}

	@GetMapping("viewallinvoices")
	public List<Invoice> getInvoices() throws InvoiceException {
		return gasBookingService.getInvoices();
	}

	@PutMapping("cylinderdelivered/{invoiceid}")
	public SuccessMessage cylinderDelivered(@PathVariable("invoiceid") int invoiceId) throws InvoiceException {
		gasBookingService.cylinderDelivered(invoiceId);
		return new SuccessMessage(CgUtil.DELIVERED); 
	}
}
