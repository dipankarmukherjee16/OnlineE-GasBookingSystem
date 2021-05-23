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


/*********************************************************************************************
 *          @author: Moinak Majumder       
 *          @version: 1.0   
 *          Description: It is a controller class that provides the services for booking a cylinder,
 *          			  generate invoice, update status of gas booking and get all invoices                                   
 *          Created at: 20-MAY-2021
 **********************************************************************************************/

@RestController
public class BookingRestController {

	@Autowired
	private IGasBookingService gasBookingService;
	
	/*********************************************************************************************
	 *          @author: Moinak Majumder       
	 *          @version: 1.0   
	 *          @return: SuccessMessage
	 *          @throws: CustomerNotFoundException, if customer not found for given customer id
	 *          Description: Insert new customer with details into the database                             
	 *          Created at: 20-MAY-2021
	 **********************************************************************************************/


	@PostMapping("bookcylinder/{customerid}")
	public SuccessMessage bookCylinder(@PathVariable("customerid") int customerId) throws CustomerNotFoundException {
		int gasBookingId = gasBookingService.bookCylinder(customerId);
		return new SuccessMessage(CgUtil.CYLINDER_BOOKED + gasBookingId);
	}
	
	/*********************************************************************************************
	 *          @author: Moinak Majumder       
	 *          @version: 1.0   
	 *          @return: SuccessMessage
	 *          @throws: CustomerNotFoundException, if customer not found for given customer id
	 *          Description: Insert new invoice into the database                             
	 *          Created at: 20-MAY-2021
	 **********************************************************************************************/
	
	@PostMapping("generateinvoice/{bookingid}/{fare}")
	public SuccessMessage generateInvoice(@PathVariable("bookingid") int bookingId, @PathVariable("fare") double fare)
			throws GasBookingNotFoundException {
		gasBookingService.generateInvoice(bookingId, fare);
		return new SuccessMessage(CgUtil.INVOICE_GENERATED);
	}
	
	/*********************************************************************************************
	 *          @author: Moinak Majumder       
	 *          @version: 1.0   
	 *          @return: List of Invoice instance
	 *          @throws: InvoiceException, if invoice not found for given invoice status
	 *          Description: Display list of invoices for given status                             
	 *          Created at: 20-MAY-2021
	 **********************************************************************************************/

	@GetMapping("viewallinvoices")
	public List<Invoice> getInvoices() throws InvoiceException {
		return gasBookingService.getInvoices();
	}
	
	/*********************************************************************************************
	 *          @author: Moinak Majumder       
	 *          @version: 1.0   
	 *          @return: SuccessMessage
	 *          @throws: InvoiceException, if invoice not found for given invoice id
	 *          Description: Update invoice status and gas booking status                             
	 *          Created at: 20-MAY-2021
	 **********************************************************************************************/

	@PutMapping("cylinderdelivered/{invoiceid}")
	public SuccessMessage cylinderDelivered(@PathVariable("invoiceid") int invoiceId) throws InvoiceException {
		gasBookingService.cylinderDelivered(invoiceId);
		return new SuccessMessage(CgUtil.DELIVERED); 
	}
}
