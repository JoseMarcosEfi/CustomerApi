package com.api.customer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.customer.entities.Billing;

public interface BillingRepository extends JpaRepository<Billing, Integer> {

}
