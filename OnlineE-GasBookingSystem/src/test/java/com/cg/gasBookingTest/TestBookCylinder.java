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

import com.cg.dao.ICustomerDao;
import com.cg.dao.IGasBookingDao;
import com.cg.dao.IInvoiceDao;
import com.cg.entity.Customer;
import com.cg.entity.GasBooking;
import com.cg.exception.CustomerNotFoundException;
import com.cg.service.GasBookingServiceImpl;
import com.cg.service.IGasBookingService;

@SpringBootTest
public class TestBookCylinder {

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
		Optional<Customer> optcust1 = Optional.of(new Customer(1,"rahim", "8574123690", "rahim64@gmail.com", "741258743699",
				"17 s k road, kolkata", "kolkata", "domestic"));
		Optional<Customer> optcust2 = Optional.empty();
		when(custDao.findById(1)).thenReturn(optcust1);
		when(custDao.findById(2)).thenReturn(optcust2);
		
		GasBooking book = new GasBooking(1004,LocalDate.of(2020, 05, 13), "DELIVERED");
		when(gasBookingDao.save(any(GasBooking.class))).thenReturn(book);
	}
	
	@Test
	@DisplayName(value="Book cylinder for customer id 1")
	public void testBookCylinder1() throws CustomerNotFoundException
	{
		assertNotNull(gasBookingService.bookCylinder(1));
	}
	
	@Test
	@DisplayName(value="Book cylinder for customer id 2")
	public void testBookCylinder2() throws CustomerNotFoundException
	{
		assertThrows(CustomerNotFoundException.class, () -> gasBookingService.bookCylinder(2));
	}
}
