package com.api.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public ResponseEntity<ServiceCall> postServiceCall(@Validated @RequestBody ServiceCall sCall) {
        if (!isValidStatusAndPriority(sCall)) {
            ServiceCall errorServiceCall = new ServiceCall();
            errorServiceCall.setObservations("Invalid status or priority value.");
            return ResponseEntity.badRequest().body(errorServiceCall);
        }

        ServiceCall newServiceCall = sCallService.createServiceCall(sCall);
        return ResponseEntity.ok(newServiceCall);
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
}
