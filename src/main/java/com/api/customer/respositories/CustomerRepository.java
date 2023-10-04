package com.api.customer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.customer.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
