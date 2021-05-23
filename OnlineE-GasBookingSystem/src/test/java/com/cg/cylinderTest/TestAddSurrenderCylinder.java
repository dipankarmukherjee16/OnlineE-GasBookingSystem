package com.cg.cylinderTest;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.ICustomerDao;
import com.cg.dao.ISurrenderCylinderDao;
import com.cg.entity.Customer;
import com.cg.entity.SurrenderCylinder;
import com.cg.exception.CustomerNotFoundException;
import com.cg.service.ISurrenderCylinderService;
import com.cg.service.SurrenderCylinderServiceImpl;

@SpringBootTest
public class TestAddSurrenderCylinder {

	@Mock
	private ISurrenderCylinderDao surrenderCylinderDao;
	
	@Mock
	private ICustomerDao customerDao;
	
	@InjectMocks
	private ISurrenderCylinderService surrenderCylinderService= new SurrenderCylinderServiceImpl();
	
	@BeforeEach
	public void beforeEach() {
		
		Optional<Customer> optcust1 = Optional.of(new Customer(1,"rahim", "8574123690", "rahim64@gmail.com", "741258743699",
				"17 s k road, kolkata", "kolkata", "domestic"));
		Optional<Customer> optcust2 = Optional.empty();
		when(customerDao.findById(1)).thenReturn(optcust1);
		when(customerDao.findById(2)).thenReturn(optcust2);
		
		SurrenderCylinder surrenderCylinder=new SurrenderCylinder(1,LocalDate.now());
		
		when(surrenderCylinderDao.save(any(SurrenderCylinder.class))).thenReturn(surrenderCylinder);
	}
	
	@Test
	@DisplayName(value="Test surrender cylinder with valid customer Id")
	public void testAddSurrenderCylinder1() throws CustomerNotFoundException
	{
		assertNotNull(surrenderCylinderService.surrenderCylinder(1));
	}

	@Test
	@DisplayName(value="Test surrender cylinder with invalid customer Id")
	public void testAddSurrenderCylinder2() throws CustomerNotFoundException
	{
		assertThrows(CustomerNotFoundException.class, ()->surrenderCylinderService.surrenderCylinder(2) );
	}


}
