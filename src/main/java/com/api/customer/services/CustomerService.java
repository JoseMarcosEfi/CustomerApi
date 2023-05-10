package com.api.customer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.customer.entities.Customer;
import com.api.customer.respositories.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository cr;

	public void createCustomer(Customer customer) {
		cr.save(customer);
	}

	public List<Customer> fetchAllCustomers() {
		return cr.findAll();
	}

	public Customer fetchCustomerById(Long id) {

		return cr.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer Not found by id" + id));
	}

	public void ChangeCustomer(Long id, Customer custom) {
		Customer existentCustomer = cr.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Customer Not Found by Id: " + id));

		existentCustomer.setName(custom.getName());
		existentCustomer.setCpf(custom.getCpf());
		existentCustomer.setEmail(custom.getEmail());
		existentCustomer.setPassword(custom.getPassword());
		cr.save(existentCustomer);

	}

	public void DeleteCustomerById(Long id) {
		cr.deleteById(id);
	}

}
