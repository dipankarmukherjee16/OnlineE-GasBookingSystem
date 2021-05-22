package com.cg.customerTest;

import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

import com.cg.dao.ICustomerDao;
import com.cg.dao.ICylinderDao;
import com.cg.entity.Customer;
import com.cg.service.CustomerServiceImpl;
import com.cg.service.ICustomerService;
import com.cg.service.IViewCustomerService;

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
		Customer customer = new Customer(1,"rahim", "8574123690", "rahim64@gmail.com", "741258743699",
				"17 s k road, kolkata", "kolkata", "active");
		when(customerDao.save(any(Customer.class))).thenReturn(customer);
	}
	

}
