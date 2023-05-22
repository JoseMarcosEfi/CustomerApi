package com.api.customer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.customer.entities.Customer;
import com.api.customer.respositories.CustomerRepository;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository cr;

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public List<Customer> get() {
		return cr.findAll();
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> getById(@PathVariable(value = "id") long id) {
		Optional<Customer> custom = cr.findById(id);
		if (custom.isPresent())
			return new ResponseEntity<Customer>(custom.get(), HttpStatusCode.valueOf(200));
		else
			return new ResponseEntity<>(HttpStatusCode.valueOf(404));
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public Customer post(@Validated @RequestBody Customer customer) {

		return cr.save(customer);
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Customer> put(@PathVariable(value = "id") Long id,
			@Validated @RequestBody Customer Newcustomer) {
		Optional<Customer> oldCustomer = cr.findById(id);
		if (oldCustomer.isPresent()) {
			Customer customer = oldCustomer.get();
			customer.setName(Newcustomer.getName());
			customer.setCpf(Newcustomer.getCpf());
			customer.setEmail(Newcustomer.getEmail());
			customer.setPassword(Newcustomer.getPassword());
			cr.save(customer);
			return new ResponseEntity<Customer>(customer, HttpStatusCode.valueOf(200));
		} else
			return new ResponseEntity<>(HttpStatusCode.valueOf(404));
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable(value = "id") long id) {
		Optional<Customer> customer = cr.findById(id);
		if (customer.isPresent()) {
			cr.delete(customer.get());
			return new ResponseEntity<>(HttpStatusCode.valueOf(204));
		} else
			return new ResponseEntity<>(HttpStatusCode.valueOf(404));
	}
}
