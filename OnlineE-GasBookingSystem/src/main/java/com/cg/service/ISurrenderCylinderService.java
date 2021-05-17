package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.dto.SurrenderCylinderDto;
import com.cg.entity.Customer;
import com.cg.entity.SurrenderCylinder;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.SurrenderCylinderNotFoundException;

public interface ISurrenderCylinderService {
	
	public Integer surrenderCylinder(SurrenderCylinderDto surrenderCylinderDto)throws CustomerNotFoundException;
	public List<Customer> viewAllSurrenderedCustomer()throws CustomerNotFoundException, SurrenderCylinderNotFoundException;
	public List<Customer> viewSurrenderCustomer(LocalDate year)throws SurrenderCylinderNotFoundException;

}
