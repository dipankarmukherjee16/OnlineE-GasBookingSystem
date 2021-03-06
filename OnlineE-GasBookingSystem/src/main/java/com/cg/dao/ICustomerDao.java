package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.Customer;

@Repository
public interface ICustomerDao extends JpaRepository<Customer, Integer> {
	@Query("from Customer cs inner join fetch cs.cylinder cd where cd.cylinderType=:ctype")
	public List<Customer> viewCustomerByType(@Param("ctype") String cylinderType);

	@Query("from Customer cs where cs.city=:cust_city")
	public List<Customer> viewCustomerByCity(@Param("cust_city") String city);

	public Customer findByMobileNumber(String mobileNumber);
	
	public Customer findByEmail(String email);
	
	public Customer findByAadharCard(String aadharCard);
	
	@Query("select cs from Customer cs where cs.customerId=:custId and cs.connectionStatus='inactive'")
	public Customer checkConnectionStatus(@Param("custId") int customerId);
	
	@Query("select cs.connectionStatus from Customer cs where cs.customerId=:custId")
	public String checkStatus(@Param("custId") int customerId);
	
	@Query("select cs from Customer cs where cs.customerId=:custId and cs.aadharCard is not null")
	public Customer checkAadharLinkage(@Param("custId") int customerId);
}