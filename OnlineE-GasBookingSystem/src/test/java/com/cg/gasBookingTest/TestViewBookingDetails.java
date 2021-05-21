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
import com.cg.exception.GasBookingNotFoundException;
import com.cg.service.IViewBookingService;
import com.cg.service.ViewBookingServiceImpl;

@SpringBootTest
public class TestViewBookingDetails {
	
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
		when(gasBookingDao.viewBookingDetailsByCustomerId(1001)).thenReturn(lst);
		when(gasBookingDao.viewBookingDetailsByCustomerId(1006)).thenReturn(lst2);
	}
	
	@Test
	@DisplayName(value = "testviewbookingdetails for customer id 1001")
	public void testViewBookingDetails1() throws GasBookingNotFoundException
	{
		assertTrue(viewBookingService.viewBookingDetails(1001).size()>0);
	}
	
	@Test
	@DisplayName(value = "testviewbookingdetails for customer id 1006")
	public void testViewBookingDetails2() throws GasBookingNotFoundException
	{
		assertThrows(GasBookingNotFoundException.class, () -> viewBookingService.viewBookingDetails(1006));
	}
	
}
