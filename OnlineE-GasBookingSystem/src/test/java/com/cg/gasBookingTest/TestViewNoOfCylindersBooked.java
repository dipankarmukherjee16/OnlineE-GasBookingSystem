package com.cg.gasBookingTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.IGasBookingDao;
import com.cg.entity.GasBooking;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.NoGasBookingDoneException;
import com.cg.service.IViewBookingService;
import com.cg.service.ViewBookingServiceImpl;

@SpringBootTest
public class TestViewNoOfCylindersBooked {
	
	@Mock
	private IGasBookingDao gasBookingDao;
	
	@InjectMocks
	private IViewBookingService viewBookingService = new ViewBookingServiceImpl();
	
	@BeforeEach
	public void beforeEach()
	{
		List<GasBooking> lst = new ArrayList<>(); 
		lst.add(new GasBooking(1004,LocalDate.of(2020, 05, 13), "DELIVERED"));
		List<GasBooking> lst2 = new ArrayList<>();
		when(gasBookingDao.noOfCylindersBookedInAYear(2020, 1004)).thenReturn(lst);
		when(gasBookingDao.noOfCylindersBookedInAYear(2020, 1005)).thenReturn(lst2);
	}
	
	@Test
	@DisplayName(value="testviewnoofcylinders for customer id 1004 and year 2020")
	public void testViewNoOfCylindersBooked1() throws NoGasBookingDoneException
	{
		//assertTrue(viewBookingService.viewNoOfCylindersBooked(2020, 1004)>0);
	}
	
	@Test
	@DisplayName(value="testviewnoofcylinders for customer id 1005 and year 2020")
	public void testViewNoOfCylindersBooked2() throws NoGasBookingDoneException
	{
		assertThrows(NoGasBookingDoneException.class, () -> viewBookingService.viewNoOfCylindersBooked(2020, 1005));
	}
}
