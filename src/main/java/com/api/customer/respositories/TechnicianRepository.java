package com.api.customer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.customer.entities.Technician;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {

}
