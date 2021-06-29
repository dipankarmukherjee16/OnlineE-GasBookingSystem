package com.cg.gasBookingTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.IGasBookingDao;
import com.cg.dao.IInvoiceDao;
import com.cg.entity.GasBooking;
import com.cg.entity.Invoice;
import com.cg.exception.DuplicateInvoiceException;
import com.cg.exception.GasBookingNotFoundException;
import com.cg.service.GasBookingServiceImpl;
import com.cg.service.IGasBookingService;

@SpringBootTest
public class TestGenerateInvoice {
	
	@Mock
	private IGasBookingDao gasBookingDao;

	@Mock
	private IInvoiceDao invoiceDao; 
	
	@InjectMocks
	IGasBookingService gasBookingService = new GasBookingServiceImpl();
	
	  @BeforeEach 
	  public void beforeEach() {
		Optional<GasBooking> optbook1 = Optional.of(new GasBooking(1,LocalDate.of(2020, 05, 13), "BOOKED"));
		Optional<GasBooking> optbook2 = Optional.empty();
		Optional<GasBooking> optbook3 = Optional.of(new GasBooking(3,LocalDate.of(2020, 06, 16), "Invoice Generated"));
		Optional<GasBooking> optbook4 = Optional.of(new GasBooking(4,LocalDate.of(2020, 06, 20), "DELIVERED"));
		
		when(gasBookingDao.findById(1)).thenReturn(optbook1);
		when(gasBookingDao.findById(2)).thenReturn(optbook2);
		when(gasBookingDao.findById(3)).thenReturn(optbook3);
		when(gasBookingDao.findById(4)).thenReturn(optbook4);
		
		Invoice invoice1=new Invoice(2000,LocalDate.of(2020, 06, 20),850.75,"Invoice Generated");
		Invoice invoice2=new Invoice(2000,LocalDate.of(2020, 06, 24),845.50,"DELIVERED");
		
		when(invoiceDao.findByBooking(optbook3.get())).thenReturn(invoice1);
		when(invoiceDao.findByBooking(optbook4.get())).thenReturn(invoice2);
		
		Invoice invoice = new Invoice(2003,LocalDate.of(2020, 05, 19),855.5,"Invoice Generated");
		optbook1.get().setStatus("Invoice Generated");
		when(gasBookingDao.save(any(GasBooking.class))).thenReturn(optbook1.get());
		when(invoiceDao.save(any(Invoice.class))).thenReturn(invoice);
	}
	
	@Test
	@DisplayName(value="testGenerateInvoice for booking id 1 and fare 855.5")
	public void testGenerateInvoice1() throws GasBookingNotFoundException, DuplicateInvoiceException
	{
		assertNotNull(gasBookingService.generateInvoice(1, 855.5));
	}
	
	@Test
	@DisplayName(value="testGenerateInvoice for booking id 2 and fare 855.5")
	public void testGenerateInvoice2() throws GasBookingNotFoundException
	{
		assertThrows(GasBookingNotFoundException.class, () -> gasBookingService.generateInvoice(2, 855.5));
	}
	
	@Test
	@DisplayName(value="testGenerateInvoice for booking id 3 and fare 850.75")
	public void testGenerateInvoice3() throws DuplicateInvoiceException
	{
		assertThrows(DuplicateInvoiceException.class, () -> gasBookingService.generateInvoice(3, 850.75));
	}
	
	@Test
	@DisplayName(value="testGenerateInvoice for booking id 4 and fare 845.50")
	public void testGenerateInvoice4() throws DuplicateInvoiceException
	{
		assertThrows(DuplicateInvoiceException.class, () -> gasBookingService.generateInvoice(4, 845.50));
	}
}
