package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Customer;
import com.cg.exception.CustomerNotFoundException;

public interface ICustomerDao extends JpaRepository<Customer, Integer>{
	
	
}