package com.cg.customerTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.ICustomerDao;
import com.cg.dao.ICylinderDao;
import com.cg.dto.CustomerDto;
import com.cg.entity.Customer;
import com.cg.entity.Cylinder;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.service.CustomerServiceImpl;
import com.cg.service.ICustomerService;

@SpringBootTest
public class TestUpdateCustomer {
	
	@Mock
	private ICustomerDao customerDao;
	
	@Mock
	private ICylinderDao cylinderDao;
	
	@InjectMocks
	private ICustomerService customerService= new CustomerServiceImpl();
	
	@BeforeEach
	public void beforeEach() {
		when(cylinderDao.findByCylinderType("domestic")).thenReturn(new Cylinder());
		when(cylinderDao.findByCylinderType("aaaa")).thenReturn(null);
		Optional<Customer> optcust1 = Optional.of(new Customer(1,"rahim", "8574123690", "rahim64@gmail.com", "741258743699",
				"17 s k road, kolkata", "kolkata", "domestic"));
		Optional<Customer> optcust2 = Optional.empty();
		when(customerDao.findById(1)).thenReturn(optcust1);
		when(customerDao.findById(2)).thenReturn(optcust2);
		
		Customer customer = new Customer(1,"rahim", "8574123690", "rahim67@gmail.com", "741258743699",
				"4 jagadis kumar lane, kolkata", "kolkata", "active");
		when(customerDao.save(any(Customer.class))).thenReturn(customer);
	}
	
	
	@Test
	@DisplayName(value = "test for update customer with valid data")
	public void testUpdateCustomer1() throws CustomerNotFoundException, CylinderTypeMismatchException {
		CustomerDto customerDto = new CustomerDto("rahim", "8574123690", "rahim64@gmail.com", "741258743699",
				"17 s k road, kolkata", "kolkata", "domestic");
		assertNotNull(customerService.updateCustomer(customerDto,1));
		
	}
	
	@Test
	@DisplayName(value = "test for update customer with invalid cylinder type")
	public void testUpdateCustomer2() throws CustomerNotFoundException, CylinderTypeMismatchException {
		CustomerDto customerDto = new CustomerDto("rahim", "8574123690", "rahim64@gmail.com", "741258743699",
				"17 s k road, kolkata", "kolkata", "aaa");
		assertThrows(CylinderTypeMismatchException.class, ()->customerService.updateCustomer(customerDto, 1));
		
	}
	

}
