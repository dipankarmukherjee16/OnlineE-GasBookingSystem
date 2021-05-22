package com.cg.cylinderTest;

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

import com.cg.dao.ISurrenderCylinderDao;
import com.cg.entity.SurrenderCylinder;
import com.cg.exception.CustomerNotFoundException;
import com.cg.service.ISurrenderCylinderService;
import com.cg.service.SurrenderCylinderServiceImpl;

@SpringBootTest
public class TestViewSurrenderCustomerByYear {

	@Mock
	private ISurrenderCylinderDao surrenderCylinderDao;
	
	@InjectMocks
	private ISurrenderCylinderService surrenderCylinderService=new SurrenderCylinderServiceImpl();
	
	@BeforeEach
	public void beforeEach()
	{
		List<SurrenderCylinder> lst= new ArrayList<>();
		lst.add(new SurrenderCylinder(1,LocalDate.of(2020, 11, 14)));
		lst.add(new SurrenderCylinder(2,LocalDate.of(2021, 1, 28)));
		List<SurrenderCylinder> lst2= new ArrayList<>();
		when(surrenderCylinderDao.viewSurrenderedCustomerByYear(2020)).thenReturn(lst);
		when(surrenderCylinderDao.viewSurrenderedCustomerByYear(12)).thenReturn(lst2);
	}
	
	@Test
	@DisplayName(value="test view surrendered customer by 2020")
	public void testViewSurrenderedCustomerByYear1() throws CustomerNotFoundException
	{
		assertTrue(surrenderCylinderService.viewSurrenderCustomer(2020).size()>0);
	}
	
	@Test
	@DisplayName(value="test view surrendered customer by 12")
	public void testViewSurrenderedCustomerByYear2() throws CustomerNotFoundException
	{
		assertThrows(CustomerNotFoundException.class, ()->surrenderCylinderService.viewSurrenderCustomer(12));
	}
}
