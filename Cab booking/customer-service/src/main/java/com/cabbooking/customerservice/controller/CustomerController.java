package com.cabbooking.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabbooking.customerservice.entity.Customer;
import com.cabbooking.customerservice.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/{id}")
	public ResponseEntity<Customer> fetchCustomerDetails(@PathVariable("id") int customerId) {
		Customer customer = customerService.findCustomer(customerId);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@GetMapping("/all")
	public List<Customer> fetchAllCustomers() {
		List<Customer> customers = customerService.allCustomer();
		return customers;
	}

	@GetMapping("/login/{Email}/{Password}")
	public Customer login(@PathVariable("Email") String Email, @PathVariable("Password") String Password) {
		return customerService.vaildCustomer(Email, Password);
	}

	@PostMapping("/registration")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		ResponseEntity<Customer> responseEntity = new ResponseEntity<>(customer, HttpStatus.CREATED);
		return responseEntity;
	}

	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		customerService.updateCustomer(customer);
		ResponseEntity<Customer> responseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") int customerId) {
		String msg = "Deleted Seccessfully";
		customerService.deleteCustomer(customerId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(msg, HttpStatus.OK);
		return responseEntity;
	}
}
