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
import com.cg.entity.Customer;
import com.cg.entity.GasBooking;
import com.cg.exception.BookingLimitReachedException;
import com.cg.exception.CustomerInactiveException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.service.GasBookingServiceImpl;
import com.cg.service.IGasBookingService;

@SpringBootTest
public class TestBookCylinder {

	@Mock
	private IGasBookingDao gasBookingDao;

	@Mock
	private ICustomerDao custDao; 

	
	@InjectMocks
	IGasBookingService gasBookingService = new GasBookingServiceImpl();
	
	@BeforeEach 
	  public void beforeEach() {
		Optional<Customer> optcust1 = Optional.of(new Customer(1,"rahim", "8574123690", "rahim64@gmail.com", "741258743699",
				"17 s k road, kolkata", "kolkata", "active"));
		Optional<Customer> optcust2 = Optional.empty();
		Optional<Customer> optcust3 = Optional.of(new Customer(3,"Ramesh", "9674123390", "ramesh39@gmail.com", "678258143459",
				"16 m l road, kolkata", "kolkata","inactive"));
		
		when(custDao.findById(1)).thenReturn(optcust1);
		when(custDao.findById(2)).thenReturn(optcust2);
		when(custDao.findById(3)).thenReturn(optcust3);
		
		when(custDao.checkConnectionStatus(3)).thenReturn(optcust3.get());
		
		GasBooking book = new GasBooking(1004,LocalDate.of(2020, 05, 13), "DELIVERED");
		when(gasBookingDao.save(any(GasBooking.class))).thenReturn(book);
	}
	
	@Test
	@DisplayName(value="testBookCylinder for customer id 1 and status active")
	public void testBookCylinder1() throws CustomerNotFoundException, BookingLimitReachedException, CustomerInactiveException
	{
		assertTrue(gasBookingService.bookCylinder(1)>0);
	}
	
	@Test
	@DisplayName(value="testBookCylinder for customer id 2")
	public void testBookCylinder2() throws CustomerNotFoundException
	{
		assertThrows(CustomerNotFoundException.class, () -> gasBookingService.bookCylinder(2));
	}
	
	@Test
	@DisplayName(value="testBookCylinder for customer id 3 and status inactive")
	public void testBookCylinder3() throws CustomerInactiveException
	{
		assertThrows(CustomerInactiveException.class, () -> gasBookingService.bookCylinder(3));
	}
}
