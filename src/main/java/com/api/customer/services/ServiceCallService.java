package com.api.customer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.customer.DTO.ServiceCallResponseDTO;
import com.api.customer.entities.ServiceCall;
import com.api.customer.respositories.ServiceCallRepository;

@Service
public class ServiceCallService {

    @Autowired
    private ServiceCallRepository serviceCallRepo;

    public ServiceCallResponseDTO createServiceCall(ServiceCall sCall) {
        Long idCustomer = sCall.getIdCustomer();

        if (!serviceCallRepo.existsByIdCustomer(idCustomer)) {
            throw new IllegalArgumentException("Customer ID does not exist.");
        }
        ServiceCall createdServiceCall = serviceCallRepo.save(sCall);
        ServiceCallResponseDTO responseDTO = convertToResponseDTO(createdServiceCall);

        return responseDTO;
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

    // other methods
    private ServiceCallResponseDTO convertToResponseDTO(ServiceCall serviceCall) {
        ServiceCallResponseDTO responseDTO = new ServiceCallResponseDTO();

        responseDTO.setId(serviceCall.getId());
        responseDTO.setPriority(serviceCall.getPriority());
        responseDTO.setStatus(serviceCall.getStatus());
        responseDTO.setObservations(serviceCall.getObservations());
        responseDTO.setTitle(serviceCall.getTitle());
        responseDTO.setValue(serviceCall.getValue());
        responseDTO.setIdTechnician(serviceCall.getIdTechnician());
        responseDTO.setIdCustomer(serviceCall.getIdCustomer());
        responseDTO.setClosingDate(serviceCall.getClosingDate());

        return responseDTO;

    }

    private void methodPutDontUse() {
        // Optional<ServiceCall> optionalServiceCall = serviceCallRepo.findById(id);
        // if (optionalServiceCall.isPresent()) {
        // ServiceCall existingServiceCall = optionalServiceCall.get();
        // Long idCustomer = sCall.getIdCustomer();

        // if (!serviceCallRepo.existsByIdCustomer(idCustomer)) {
        // throw new IllegalArgumentException("Customer ID does not exist.");
        // }
        // ServiceCall changeServiceCall = serviceCallRepo.save(existingServiceCall);
        // ServiceCallResponseDTO responseDTO = convertToResponseDTO(changeServiceCall);

        // return responseDTO;

        // }
        // return null;
    }
}