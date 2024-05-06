package com.cabbooking.customerservice.service;

import java.util.List;

import com.cabbooking.customerservice.entity.Customer;
import com.cabbooking.customerservice.exception.IdInvalid;
import com.cabbooking.customerservice.exception.NullException;


public interface CustomerService {

	public Customer saveCustomer(Customer customer);

	public Customer findCustomer(int customerId) throws IdInvalid;

	public Customer updateCustomer(Customer customer) throws IdInvalid;

	public String deleteCustomer(int id) throws IdInvalid;

	public List<Customer> allCustomer() throws NullException;

	public Customer vaildCustomer(String Email, String Password) throws IdInvalid;


}
