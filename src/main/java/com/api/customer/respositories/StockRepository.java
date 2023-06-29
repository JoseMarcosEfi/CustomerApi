package com.api.customer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.customer.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

}
