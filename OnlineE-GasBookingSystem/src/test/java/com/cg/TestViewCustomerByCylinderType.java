package com.cg;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.ICustomerDao;
import com.cg.entity.Customer;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.service.CustomerServiceImpl;
import com.cg.service.ICustomerService;
import com.cg.service.IViewCustomerService;
import com.cg.service.ViewCustomerServiceImpl;

@SpringBootTest
public class TestViewCustomerByCylinderType {

	@Mock
	private ICustomerDao customerDao;

	@InjectMocks
	private IViewCustomerService viewCustomerService = new ViewCustomerServiceImpl();

	@BeforeEach
	public void beforeEach() {
		List<Customer> lst = new ArrayList<>();
		lst.add(new Customer(1001, "rahim", "8574123690", "rahim64@gmail.com", "741258743699", "17 s k road, kolkata",
				"kolkata", "active"));
		lst.add(new Customer(1002, "ram", "5414123674", "ram74@gmail.com", "9518743699", "9 gopal lal road, odissa",
				"puri", "active"));
		List<Customer> lst2 = new ArrayList<>();
		when(customerDao.viewCustomerByType("domestic")).thenReturn(lst);
		when(customerDao.viewCustomerByType("aaaa")).thenReturn(lst2);
	}

	@Test
	@DisplayName(value = "test view customer by cylinder type domestic")
	public void testViewCustomerByCylinderType1() throws CustomerNotFoundException, CylinderTypeMismatchException {

		assertTrue(viewCustomerService.viewCustomerbyCylinderType("domestic").size()>0);
	}

	@Test
	@DisplayName(value = "test view customer by cylinder type aaaa")
	public void testViewCustomerByCylinderType2() throws CustomerNotFoundException, CylinderTypeMismatchException {

		assertThrows(CustomerNotFoundException.class, ()->viewCustomerService.viewCustomerbyCylinderType(null));
	}
}
