package com.api.customer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.customer.entities.ServiceCall;
import com.api.customer.respositories.ServiceCallRepository;

@Service
public class ServiceCallService {

    @Autowired
    private ServiceCallRepository serviceCallRepo;

    public ServiceCall createServiceCall(ServiceCall sCall) {
        return serviceCallRepo.save(sCall);
    }

    public List<ServiceCall> listAllServiceCall() {
        return serviceCallRepo.findAll();
    }

    public ServiceCall FindById(int id) {
        Optional<ServiceCall> optionalServiceCall = serviceCallRepo.findById(id);
        return optionalServiceCall.orElse(null);
    }

    public ServiceCall changeServiceCall(int id, ServiceCall sCall) {
        Optional<ServiceCall> optionalServiceCall = serviceCallRepo.findById(id);
        if (optionalServiceCall.isPresent()) {
            sCall.setId(id);
            return serviceCallRepo.save(sCall);
        }
        return null;
    }

    public void deleteServiceCall(int id) {
        serviceCallRepo.deleteById(id);
    }
}