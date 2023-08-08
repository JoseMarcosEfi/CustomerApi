package com.api.customer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.customer.entities.ServiceCall;

public interface ServiceCallRepository extends JpaRepository<ServiceCall, Integer> {

}
