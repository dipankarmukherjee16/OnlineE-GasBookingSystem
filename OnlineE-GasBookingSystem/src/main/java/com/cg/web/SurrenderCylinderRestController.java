package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.SurrenderCylinderDto;
import com.cg.entity.Customer;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.SurrenderCylinderNotFoundException;
import com.cg.service.ISurrenderCylinderService;

@RestController
public class SurrenderCylinderRestController {
	
	@Autowired
	private ISurrenderCylinderService surrenderCylinderService;
	
	@GetMapping("viewallsurrenderedcustomer")
	public List<Customer> viewAllSurrenderedCustomer()throws CustomerNotFoundException, SurrenderCylinderNotFoundException{
		return surrenderCylinderService.viewAllSurrenderedCustomer();
	}
	
	@GetMapping("viewsurrendercustomer/{year}")
	public List<Customer> viewSurrenderCustomer(@PathVariable("year") int year)throws CustomerNotFoundException{
		return surrenderCylinderService.viewSurrenderCustomer(year);
	}
	
	

}
