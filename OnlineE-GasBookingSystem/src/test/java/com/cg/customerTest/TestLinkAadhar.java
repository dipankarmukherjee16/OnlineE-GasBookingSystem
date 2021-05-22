package com.cg.customerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.ICustomerDao;
import com.cg.entity.Customer;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.ValidateException;
import com.cg.service.CustomerServiceImpl;
import com.cg.service.ICustomerService;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestLinkAadhar {
	
	@Mock
	private ICustomerDao customerDao;
	
	@InjectMocks
	private ICustomerService service= new CustomerServiceImpl();
	
	@BeforeEach
	public void beforeEach() {
		Optional<Customer> optcust=Optional.of(new Customer());
		Optional<Customer> optcust2= Optional.empty();
		when(customerDao.findById(1)).thenReturn(optcust);
		when(customerDao.findById(2)).thenReturn(optcust2);
		when(customerDao.save(any(Customer.class))).thenReturn(new Customer());
	}
	
	@Test
	public void testLinkAdhar1() throws CustomerNotFoundException, ValidateException {
		assertTrue(service.linkAadhar(1, "574185478523"));
	}
	
	@Test
	public void testLinkAdhar2() {
		assertThrows(CustomerNotFoundException.class,()->service.linkAadhar(2, "5871"));
	}
	
	@Test
	public void testLinkAdhar3() {
		assertThrows(ValidateException.class, ()-> service.linkAadhar(1, "aaaa"));
	}
	

}
