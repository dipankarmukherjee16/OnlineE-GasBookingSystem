package com.cg.gasBookingTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.cg.dao.ICustomerDao;
import com.cg.dao.IGasBookingDao;
import com.cg.dao.IInvoiceDao;
import com.cg.entity.GasBooking;
import com.cg.entity.Invoice;
import com.cg.exception.InvoiceException;
import com.cg.service.GasBookingServiceImpl;
import com.cg.service.IGasBookingService;

@SpringBootTest
public class TestCylinderDelivered {

	@Mock
	private IGasBookingDao gasBookingDao;

	@Mock
	private ICustomerDao custDao;

	@Mock
	private IInvoiceDao invoiceDao;
	
	@InjectMocks
	IGasBookingService gasBookingService = new GasBookingServiceImpl();
	
	  @BeforeEach 
	  public void beforeEach() {
		  	Optional<Invoice> optinvoice1 = Optional.of(new Invoice(2003,LocalDate.of(2020, 05, 19),855.5,"Invoice Generated"));
		  	Optional<Invoice> optinvoice2 = Optional.empty();
		  	
		  	when(invoiceDao.findById(2003)).thenReturn(optinvoice1);
		  	when(invoiceDao.findById(2005)).thenReturn(optinvoice2);
		  	
		  	Invoice invoice = optinvoice1.get();
  			invoice.setInvoiceStatus("DELIVERED");
		  	GasBooking book = new GasBooking(1,LocalDate.of(2020, 05, 13), "Invoice Generated");
		  	invoice.setBooking(book);
		  	invoice.getBooking().setStatus("DELIVERED");
		  	
		  	when(gasBookingDao.save(any(GasBooking.class))).thenReturn(invoice.getBooking());
			when(invoiceDao.save(any(Invoice.class))).thenReturn(invoice);
	}
	  
	  @Test
	  @DisplayName(value="testCylinderDelivered for invoice id 2003")
	  public void testCylinderDelivered1() throws InvoiceException
	  {
		  assertTrue(gasBookingService.cylinderDelivered(2003)==true);
	  }
	  
	  @Test
	  @DisplayName(value="testCylinderDelivered for invoice id 2005")
	  public void testCylinderDelivered2() throws InvoiceException
	  {
		  assertThrows(InvoiceException.class, () -> gasBookingService.cylinderDelivered(2005));
	  }
	  
}
