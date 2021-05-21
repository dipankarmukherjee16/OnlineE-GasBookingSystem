package com.cg.customerTest;

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
import com.cg.util.CgUtil;

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

	CustomerDto customerDto;
	Customer customer;
	
	
	
	/*
	 * @BeforeEach public void beforeEach() {
	 * when(cylinderDao.findByCylinderType("domestic")).thenReturn(new Cylinder());
	 * when(cylinderDao.findByCylinderType("aaaa")).thenReturn(null);
	 * 
	 * customerDto= new CustomerDto(); customer= new Customer();
	 * 
	 * customerDto.setUserName("rahim"); customerDto.setMobileNumber("8574123690");
	 * customerDto.setEmail("rahim64@gmail.com");
	 * customerDto.setAadharCard("741258743699");
	 * customerDto.setAddress("17 s k road, kolkata");
	 * customerDto.setCity("kolkata"); customerDto.setCylinderType("domestic");
	 * 
	 * customer.setCustomerId(1); customer.setUserName(customerDto.getUserName());
	 * customer.setMobileNumber(customerDto.getMobileNumber());
	 * customer.setEmail(customerDto.getEmail());
	 * customer.setAadharCard(customerDto.getAadharCard());
	 * customer.setAddress(customerDto.getAddress());
	 * customer.setCity(customerDto.getCity());
	 * customer.setConnectionStatus(CgUtil.CONNECTION_ACTIVE);
	 * 
	 * when(customerDao.save(any(Customer.class))).the
	 * 
	 * }
	 */
	 
	 

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
