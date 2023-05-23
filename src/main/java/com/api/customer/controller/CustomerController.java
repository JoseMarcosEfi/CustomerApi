package com.api.customer.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.customer.entities.Customer;
import com.api.customer.services.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(value = "*")
public class CustomerController {

	@Autowired
	private CustomerService cs;

	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customers = cs.fetchAllCustomers();
		if (customers.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(customers);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") long id) {
		Customer customer = cs.fetchCustomerById(id);
		if (customer != null) {
			return ResponseEntity.ok().body(customer);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Customer> CustomerSave(@Validated @RequestBody Customer customer) {
		Customer NewCustomer = cs.createCustomer(customer);
		return new ResponseEntity<>(NewCustomer, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> putCustomer(@PathVariable Long id, @Validated @RequestBody Customer customer) {
		customer = cs.fetchCustomerById(id);
		if (customer != null) {
			return ResponseEntity.ok().body(customer);
		}
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable long id) {
		cs.DeleteCustomerById(id);
		return ResponseEntity.noContent().build();

	}
}
