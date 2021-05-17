package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Customer;
import com.cg.exception.CustomerNotFoundException;

@Repository
public interface ICustomerDao extends JpaRepository<Customer, Integer>{
	
	
}