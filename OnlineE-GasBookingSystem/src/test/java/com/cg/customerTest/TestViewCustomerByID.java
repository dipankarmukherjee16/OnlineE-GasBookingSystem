package com.cg.customerTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.ICustomerDao;
import com.cg.entity.Customer;
import com.cg.exception.CustomerNotFoundException;
import com.cg.service.IViewCustomerService;
import com.cg.service.ViewCustomerServiceImpl;

@SpringBootTest
public class TestViewCustomerByID {

	@Mock
	private ICustomerDao customerDao;

	@InjectMocks
	private IViewCustomerService viewCustomerService = new ViewCustomerServiceImpl();

	@BeforeEach
	public void beforeEach() {
		Optional<Customer> optcust1 = Optional.of(new Customer());
		Optional<Customer> optcust2 = Optional.empty();
		when(customerDao.findById(1001)).thenReturn(optcust1);
		when(customerDao.findById(1002)).thenReturn(optcust2);

	}

	@Test
	@DisplayName(value = "testviewbyid for 1001")
	public void testviewbyCustomerId1() throws CustomerNotFoundException {
		assertNotNull(viewCustomerService.viewCustomerbyId(1001));

	}

	@Test
	@DisplayName(value = "testviewbyid for 1002")
	public void testviewbyCustomerId2() {
		assertThrows(CustomerNotFoundException.class, () -> viewCustomerService.viewCustomerbyId(1002));

	}

}
