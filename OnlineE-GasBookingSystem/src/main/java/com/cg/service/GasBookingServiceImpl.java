package com.cg.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.ICustomerDao;
import com.cg.dao.IGasBookingDao;
import com.cg.dao.IInvoiceDao;

import com.cg.entity.Customer;
import com.cg.entity.GasBooking;
import com.cg.entity.Invoice;
import com.cg.exception.BookingLimitReachedException;
import com.cg.exception.CustomerInactiveException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.DuplicateInvoiceException;
import com.cg.exception.GasBookingNotFoundException;
import com.cg.exception.InvoiceException;
import com.cg.util.CgUtil;


/*********************************************************************************************
 *          @author: Moinak Majumder        
 *          @version: 1.0   
 *          Description: It is a service class that provides the services for booking a cylinder,
 *          			  generate invoice, update status of gas booking and get all invoices                            
 *          Created at: 19-MAY-2021
 **********************************************************************************************/


@Service("gasbookingservice")
@Transactional
public class GasBookingServiceImpl implements IGasBookingService {

	@Autowired
	private IGasBookingDao bookDao;

	@Autowired
	private ICustomerDao custDao;

	@Autowired
	private IInvoiceDao invoiceDao;
	
	/*********************************************************************************************
	 *          @author: Moinak Majumder        
	 *          @version: 1.0   
	 *          @return: gasBookingId
	 * 			@throws BookingLimitReachedException 
	 * 			@throws CustomerInactiveException 
	 *          @throws: CustomerNotFoundException, if customer not found for given customer id
	 *          Description: Insert new gas booking details into the database                             
	 *          Created at: 18-MAY-2021
	 **********************************************************************************************/

	@Override
	@Transactional
	public Integer bookCylinder(Integer customerId) throws CustomerNotFoundException, BookingLimitReachedException, CustomerInactiveException{
		Optional<Customer> opcust = custDao.findById(customerId);
		if (!opcust.isPresent())
			throw new CustomerNotFoundException(CgUtil.CUSTOMERNOTFOUND);
		if(custDao.checkConnectionStatus(customerId)!=null)
			throw new CustomerInactiveException(CgUtil.CUSTOMER_INACTIVE);
		List<GasBooking> lst = bookDao.checkBookingLimit(customerId);
		if(lst.size()==12)
			throw new BookingLimitReachedException(CgUtil.BOOKING_LIMIT_REACHED);
		GasBooking book = new GasBooking();

		book.setBookingDate(LocalDate.now());
		book.setStatus(CgUtil.STATUS_BOOKED);

		Customer cust = opcust.get();
		book.setCustomer(cust);
		GasBooking persistedBook = bookDao.save(book);
		return persistedBook.getGasBookingId();
	}
	
	/*********************************************************************************************
	 *          @author: Moinak Majumder        
	 *          @version: 1.0   
	 *          @return: Invoice instance
	 * @throws DuplicateInvoiceException 
	 *          @throws: GasBookingNotFoundException, if booking not found for given booking id
	 *          Description: Insert new invoice into the database                              
	 *          Created at: 18-MAY-2021
	 **********************************************************************************************/

	@Override
	@Transactional
	public Invoice generateInvoice(Integer bookingId, Double fare) throws GasBookingNotFoundException, DuplicateInvoiceException {
		Optional<GasBooking> opbook = bookDao.findById(bookingId);
		if (!opbook.isPresent())
			throw new GasBookingNotFoundException(CgUtil.BOOKINGNOTFOUND); 
		GasBooking book = opbook.get();

		if(invoiceDao.findByBooking(book)!=null) {
			throw new DuplicateInvoiceException(CgUtil.DUPLICATE_INVOICE);
		}
		Invoice invoice = new Invoice();
		invoice.setInvoiceDate(LocalDate.now());
		invoice.setBillAmount(fare);
		invoice.setBooking(book);
		invoice.setInvoiceStatus(CgUtil.INVOICE_GENERATED);
		book.setStatus(CgUtil.INVOICE_GENERATED);
		bookDao.save(book); 
		invoiceDao.save(invoice);
		return invoice;
	}
	
	/*********************************************************************************************
	 *          @author: Moinak Majumder        
	 *          @version: 1.0   
	 *          @return: List of Invoice instance
	 *          @throws: InvoiceException, if invoice not found for given invoice status
	 *          Description: Display list of invoices for given status                              
	 *          Created at: 18-MAY-2021
	 **********************************************************************************************/

	@Override
	public List<Invoice> getInvoices() throws InvoiceException {
		List<Invoice> invoices = invoiceDao.findByInvoiceStatus(CgUtil.INVOICE_GENERATED);
		if (invoices.isEmpty()) {
			throw new InvoiceException(CgUtil.INVOICE_NOTFOUND);
		}
		invoices.sort((e1, e2) -> e1.getInvoiceDate().compareTo(e2.getInvoiceDate()));
		return invoices;
	}
	
	/*********************************************************************************************
	 *          @author: Moinak Majumder        
	 *          @version: 1.0   
	 *          @return: true
	 *          @throws: InvoiceException, if invoice not found for given invoice id
	 *          Description: Update invoice status and gas booking status                              
	 *          Created at: 18-MAY-2021
	 **********************************************************************************************/

	@Override
	public boolean cylinderDelivered(Integer invoiceId) throws InvoiceException {
		Optional<Invoice> optinvoice = invoiceDao.findById(invoiceId);
		if (!optinvoice.isPresent()) {
			throw new InvoiceException(CgUtil.INVOICE_EMPTY);
		}
		Invoice invoice = optinvoice.get();
		invoice.setInvoiceStatus(CgUtil.DELIVERED);
		invoiceDao.save(invoice);
		GasBooking booking = invoice.getBooking();
		booking.setStatus(CgUtil.DELIVERED);
		bookDao.save(booking);
		return true;
	}

}
