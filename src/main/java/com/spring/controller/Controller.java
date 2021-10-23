package com.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dao.CustomerRepo;
import com.spring.entity.Customer;

@RestController
public class Controller {
	
	@Autowired
	private CustomerRepo repo;
	
	@GetMapping("/")
	public String wish() {
		return "Spring Boot + Data JPA CRUD!!";
	}
	@PostMapping("/addCustomer")
	public Customer addCustomer(@RequestBody Customer customer) {
		return repo.save(customer);
	}
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return repo.findAll();
	}
	@GetMapping("/customer/{id}")
	public Optional<Customer> getCustomerById(@PathVariable int id) {
		return repo.findById(id);
	}
	@DeleteMapping("/customer/{id}")
	public String deleteCustomerById(@PathVariable int id) {
		if(repo.findById(id).isPresent()) {
			repo.deleteById(id);			
			return "Customer deleted";
		} else {
			return "Id not found";
		}
	}
	@PutMapping("/customer/{id}")
	public Optional<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer c) {
		Optional<Customer> customer = repo.findById(id);
		if(customer.isPresent()) {
			customer.get().setFirstName(c.getFirstName());
			customer.get().setLastName(c.getLastName());
			customer.get().setEmail(c.getEmail());			
			repo.save(customer.get());
		}		
		return customer;
	}
}
