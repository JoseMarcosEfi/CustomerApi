package com.api.customer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.customer.entities.Billing;
import com.api.customer.respositories.BillingRepository;

@Service
public class BillingService {
    @Autowired
    private BillingRepository bRepository;

    public Billing create(Billing billing) {
        return bRepository.save(billing);
    }

    public List<Billing> findAll() {
        return bRepository.findAll();
    }

    public Billing findById(Integer id) {
        Optional<Billing> obj = bRepository.findById(id);
        return obj.orElse(null);
    }

    public void delete(Integer id) {
        bRepository.deleteById(id);
    }
}
