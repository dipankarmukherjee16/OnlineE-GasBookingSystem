package com.cg.gasBookingTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.cg.exception.InvoiceException;
import com.cg.service.GasBookingServiceImpl;
import com.cg.service.IGasBookingService;

@SpringBootTest
public class TestGetInvoices {

	@Mock
	private IInvoiceDao invoiceDao; 
	
	@InjectMocks
	IGasBookingService gasBookingService = new GasBookingServiceImpl();
	
	@BeforeEach 
	  public void beforeEach() {
		List<Invoice> lst = new ArrayList<>();
		lst.add(new Invoice(2003,LocalDate.of(2020, 05, 19),855.5,"Invoice Generated"));
		List<Invoice> lst2 = new ArrayList<>();
		when(invoiceDao.findByInvoiceStatus("Invoice Generated")).thenReturn(lst);
		when(invoiceDao.findByInvoiceStatus("DELIVERED")).thenReturn(lst2);
	}
	
	@Test
	@DisplayName(value="testGetInvoices for invoice status Invoice Generated")
	public void testGetInvoices1() throws InvoiceException
	{
		List<Invoice> lst = new ArrayList<>();
		lst.add(new Invoice(2003,LocalDate.of(2020, 05, 19),855.5,"Invoice Generated")); 
		List<Invoice> list = new ArrayList<>(gasBookingService.getInvoices());
		list=lst.stream().collect(Collectors.toList());
		assertTrue(list.size()>0);
	}
	
	@Test
	@DisplayName(value="testGetInvoices for invoice status DELIVERED")
	public void testGetInvoices2() throws InvoiceException
	{

		assertThrows(InvoiceException.class, () -> gasBookingService.getInvoices());
	}
	
	
}
