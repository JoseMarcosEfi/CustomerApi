package com.api.customer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.customer.entities.Customer;
import com.api.customer.respositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository cr;

	public Customer createCustomer(Customer customer) {
		return cr.save(customer);
	}

	public List<Customer> fetchAllCustomers() {
		return cr.findAll();
	}

	public Customer fetchCustomerById(Long id) {
		Optional<Customer> optionalCustomer = cr.findById(id);
		return optionalCustomer.orElse(null);
	}

	public Customer ChangeCustomer(Long id, Customer customer) {
		Optional<Customer> optionalCustomer = cr.findById(id);
		if (optionalCustomer.isPresent()) {
			customer.setId(id);
			return cr.save(customer);
		}
		return null;
	}

	public void DeleteCustomerById(Long id) {
		cr.deleteById(id);
	}

}
