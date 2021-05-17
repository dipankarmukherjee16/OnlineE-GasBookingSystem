package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.Customer;
import com.cg.exception.CustomerNotFoundException;

@Repository
public interface ICustomerDao extends JpaRepository<Customer, Integer>{
	@Query("from Customer cs inner join fetch cs.cylinder cd where cd.cylinderType=:ctype")
	public 	List<Customer> viewCustomerByType(@Param("ctype") String cylinderType);
	
	@Query("from Customer cs where cs.city=:cust_city")
	public List<Customer> viewCustomerByCity(@Param("cust_city") String city);
	
	@Query("from Customer cs where cs.customerId=:id")
	public Customer findByCustomerId(@Param("id") int customerId);
}