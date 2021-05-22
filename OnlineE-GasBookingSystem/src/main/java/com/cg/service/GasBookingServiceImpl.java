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
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.GasBookingNotFoundException;
import com.cg.exception.InvoiceException;
import com.cg.util.CgUtil;

@Service("gasbookingservice")
@Transactional
public class GasBookingServiceImpl implements IGasBookingService {

	@Autowired
	private IGasBookingDao bookDao;

	@Autowired
	private ICustomerDao custDao;

	@Autowired
	private IInvoiceDao invoiceDao;

	@Override
	@Transactional
	public Integer bookCylinder(Integer customerId) throws CustomerNotFoundException {
		Optional<Customer> opcust = custDao.findById(customerId);
		if (!opcust.isPresent())
			throw new CustomerNotFoundException(CgUtil.CUSTOMERNOTFOUND);
		GasBooking book = new GasBooking();

		book.setBookingDate(LocalDate.now());
		book.setStatus(CgUtil.STATUS_BOOKED);

		Customer cust = opcust.get();
		book.setCustomer(cust);
		GasBooking persistedBook = bookDao.save(book);
		return persistedBook.getGasBookingId();
	}

	@Override
	@Transactional
	public boolean cancelGasBooking(Integer bookingId) throws GasBookingNotFoundException {
		Optional<GasBooking> opbook = bookDao.findById(bookingId);
		if (!opbook.isPresent())
			throw new GasBookingNotFoundException(CgUtil.BOOKINGNOTFOUND);
		GasBooking book = opbook.get();
		bookDao.delete(book);
		return true;
	}

	@Override
	@Transactional
	public Invoice generateInvoice(Integer bookingId, Double fare) throws GasBookingNotFoundException {
		Optional<GasBooking> opbook = bookDao.findById(bookingId);
		if (!opbook.isPresent())
			throw new GasBookingNotFoundException(CgUtil.BOOKINGNOTFOUND);
		GasBooking book = opbook.get();

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

	@Override
	public List<Invoice> getInvoices() throws InvoiceException {
		List<Invoice> invoices = invoiceDao.findByInvoiceStatus(CgUtil.INVOICE_GENERATED);
		if (invoices.isEmpty()) {
			throw new InvoiceException(CgUtil.INVOICE_EMPTY);
		}
		invoices.sort((e1, e2) -> e1.getInvoiceDate().compareTo(e2.getInvoiceDate()));
		return invoices;
	}

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
