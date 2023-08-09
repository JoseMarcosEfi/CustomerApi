package com.api.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.customer.DTO.ServiceCallResponseDTO;
import com.api.customer.entities.Customer;
import com.api.customer.entities.ServiceCall;
import com.api.customer.entities.Technician;
import com.api.customer.enums.Priority;
import com.api.customer.enums.Status;
import com.api.customer.services.CustomerService;
import com.api.customer.services.ServiceCallService;
import com.api.customer.services.TechnicianService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/serviceCall")
public class ServiceCallController {

    @Autowired
    public ServiceCallService sCallService;
    @Autowired
    public TechnicianService techService;
    @Autowired
    public CustomerService customService;

    @GetMapping
    public ResponseEntity<List<ServiceCall>> findAll() {
        List<ServiceCall> listServiceCalls = this.sCallService.listAllServiceCall();

        for (ServiceCall serviceCall1 : listServiceCalls) {
            Technician technician = techService.findById(serviceCall1.getIdTechnician());
            Customer customer = customService.findCustomerById(serviceCall1.getIdCustomer());

            serviceCall1.setNameTechnician(technician != null ? technician.getName() : "Technician Deleted!");
            serviceCall1.setNameCustomer(customer != null ? customer.getName() : "Customer deleted");
        }

        return ResponseEntity.ok().body(listServiceCalls);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceCall> GetById(@PathVariable(value = "id") int id) {
        ServiceCall sCall = sCallService.FindById(id);
        if (sCall != null) {
            Technician technician = techService.findById(sCall.getIdTechnician());
            Customer customer = customService.findCustomerById(sCall.getIdCustomer());

            sCall.setNameTechnician(technician != null ? technician.getName() : "Technician deleted");
            sCall.setNameCustomer(customer != null ? customer.getName() : "Customer deleted");
            return ResponseEntity.ok().body(sCall);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> postServiceCall(@Validated @RequestBody ServiceCall sCall) {
        if (!isValidStatusAndPriority(sCall)) {
            ServiceCall errorServiceCall = new ServiceCall();
            errorServiceCall.setObservations("Invalid status or priority value.");
            return ResponseEntity.badRequest().body(errorServiceCall);
        }
        try {
            ServiceCallResponseDTO newServiceCall = sCallService.createServiceCall(sCall);
            return ResponseEntity.ok().body(newServiceCall);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error creating service call: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putServiceCAll(@PathVariable(value = "id") int id,
            @Validated @RequestBody ServiceCall sCall) {
        sCall = sCallService.changeServiceCall(id, sCall);
        if (sCall != null) {
            return ResponseEntity.ok().body(sCall);
        }
        return ResponseEntity.notFound().build();
    }

    private boolean isValidStatusAndPriority(ServiceCall sCall) {
        try {
            Status.valueOf(sCall.getStatus().getStatus());
            Priority.valueOf(sCall.getPriority().getPriority());
            return true;

        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private ServiceCall convertToServiceCall(ServiceCallResponseDTO responseDTO) {
        ServiceCall serviceCall = new ServiceCall();
        serviceCall.setId(responseDTO.getId());
        serviceCall.setPriority(responseDTO.getPriority());
        serviceCall.setStatus(responseDTO.getStatus());
        serviceCall.setObservations(responseDTO.getObservations());
        serviceCall.setTitle(responseDTO.getTitle());
        serviceCall.setValue(responseDTO.getValue());
        serviceCall.setIdTechnician(responseDTO.getIdTechnician());
        serviceCall.setIdCustomer(responseDTO.getIdCustomer());
        serviceCall.setClosingDate(responseDTO.getClosingDate());

        return serviceCall;
    }

}
