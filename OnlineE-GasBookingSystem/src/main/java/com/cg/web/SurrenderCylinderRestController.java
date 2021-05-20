package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.SuccessMessage;
import com.cg.entity.Customer;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.SurrenderCylinderNotFoundException;
import com.cg.service.ISurrenderCylinderService;
import com.cg.util.CgUtil;

@RestController
public class SurrenderCylinderRestController {

	@Autowired
	private ISurrenderCylinderService surrenderCylinderService;

	@PostMapping("surrendercylinder/{customerid}")
	public SuccessMessage surrenderCylinder(@PathVariable("customerid") int customerId)
			throws CustomerNotFoundException {
		int surrenderId = surrenderCylinderService.surrenderCylinder(customerId);
		return new SuccessMessage(CgUtil.SURRENDER_CYLINDER + surrenderId);
	}

	@GetMapping("viewallsurrenderedcustomer")
	public List<Customer> viewAllSurrenderedCustomer()
			throws CustomerNotFoundException, SurrenderCylinderNotFoundException {
		return surrenderCylinderService.viewAllSurrenderedCustomer();
	}

	@GetMapping("viewsurrendercustomer/{year}")
	public List<Customer> viewSurrenderCustomer(@PathVariable("year") int year) throws CustomerNotFoundException {
		return surrenderCylinderService.viewSurrenderCustomer(year);
	}

}
