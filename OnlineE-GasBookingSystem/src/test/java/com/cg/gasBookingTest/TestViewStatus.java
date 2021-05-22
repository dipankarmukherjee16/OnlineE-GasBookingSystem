package com.cg.gasBookingTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.cg.entity.GasBooking;
import com.cg.exception.GasBookingNotFoundException;
import com.cg.service.IViewBookingService;
import com.cg.service.ViewBookingServiceImpl;
import com.cg.util.CgUtil;

@SpringBootTest
public class TestViewStatus {
	
	@Mock
	private IGasBookingDao gasBookingDao;
	
	@InjectMocks
	private IViewBookingService viewBookingService = new ViewBookingServiceImpl();
	
	@BeforeEach
	public void beforeEach()
	{
		
		  Optional<GasBooking> optcust1 = Optional.of(new GasBooking(1004,LocalDate.of(2020, 05, 13), "DELIVERED"));
		  Optional<GasBooking> optcust2 = Optional.empty();
		 
		when(gasBookingDao.findById(1004)).thenReturn(optcust1);
		when(gasBookingDao.findById(1006)).thenReturn(optcust2);
	}
	
	@Test
	@DisplayName(value = "testviewstatus for id 1004")
	public void testViewStatus1() throws GasBookingNotFoundException
	{
		assertEquals(CgUtil.DELIVERED,viewBookingService.viewStatus(1004));
	}
	
	@Test
	@DisplayName(value = "testviewstatus for id 1006")
	public void testViewStatus2() throws GasBookingNotFoundException
	{
		assertThrows(GasBookingNotFoundException.class, () -> viewBookingService.viewStatus(1006));
	}
}
