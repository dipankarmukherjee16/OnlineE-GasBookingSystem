package com.cg.repository;

import java.util.List;

import com.cg.entity.Customer;
import com.cg.exception.CustomerNotFoundException;

public interface ICustomerRepository {
	public Customer insertCustomer(Customer customer);
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
	public Customer deleteCustomer(Customer customer) throws CustomerNotFoundException;
	public List<Customer>viewCustomers();
	public Customer viewCustomer(int customerId);
	public Customer validateCustomer(String username, String password)throws CustomerNotFoundException;
}