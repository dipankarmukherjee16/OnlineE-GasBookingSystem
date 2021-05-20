package com.cg;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.ICustomerDao;
import com.cg.dao.ICylinderDao;
import com.cg.dao.IGasBookingDao;
import com.cg.dto.CustomerDto;
import com.cg.entity.Customer;
import com.cg.entity.Cylinder;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.service.CustomerServiceImpl;
import com.cg.service.ICustomerService;

@SpringBootTest
public class TestInsertCustomer {

	@Mock
	private ICustomerDao customerDao;

	@Mock
	private ICylinderDao cylinderDao;

	@Mock
	private IGasBookingDao gasBookingDao;

	@InjectMocks
	private ICustomerService customerService = new CustomerServiceImpl();

	@BeforeEach
	public void beforeEach() {
		when(cylinderDao.findByCylinderType("domestic")).thenReturn(new Cylinder());
		when(cylinderDao.findByCylinderType("aaaa")).thenReturn(null);
		Customer persistedCustomer = new Customer(1001,"rahim", "8574123690", "rahim64@gmail.com", "741258743699",
				"17 s k road, kolkata", "kolkata", "active");
		when(customerDao.save(any(Customer.class))).thenReturn(persistedCustomer);

	}

	@Test
	@DisplayName(value = "test insert customer for domestic")
	public void testInsertCustomer1() throws CylinderTypeMismatchException {
		CustomerDto customerDto = new CustomerDto("rahim", "8574123690", "rahim64@gmail.com", "741258743699",
				"17 s k road, kolkata", "kolkata", "domestic");
		assertNotNull(customerService.insertCustomer(customerDto));
	}

	@Test
	@DisplayName(value = "test insert customer for aaaa")
	public void testInsertCustomer2() throws CylinderTypeMismatchException {
		CustomerDto customerDto = new CustomerDto("rahim", "8574123690", "rahim64@gmail.com", "741258743699",
				"17 s k road, kolkata", "kolkata", "aaaa");
		assertThrows(CylinderTypeMismatchException.class, () -> customerService.insertCustomer(customerDto));
	}

}
