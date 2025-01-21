package com.api.customer.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.customer.entities.Billing;
import com.api.customer.entities.Customer;
import com.api.customer.entities.Technician;
import com.api.customer.respositories.ServiceCallRepository;
import com.api.customer.services.BillingService;
import com.api.customer.services.CustomerService;
import com.api.customer.services.TechnicianService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/billings")
public class BillingController {

    @Autowired
    private BillingService bService;
    @Autowired
    private ServiceCallRepository sCallRepository;
    @Autowired
    private TechnicianService technicianService;
    @Autowired
    private CustomerService customerService;

    private BigDecimal totalValue;

    @GetMapping
    public ResponseEntity<List<Billing>> findAll() {
        List<Billing> listBilling = this.bService.findAll();

        for (Billing billing : listBilling) {

            Customer custom = customerService.findCustomerById(billing.getIdCustomer());
            Technician tech = technicianService.findTechById(billing.getIdTechnician());
            
            billing.setNameTechnician(tech != null ? tech.getName() : "Technician Deleted");
            billing.setNameCustomer(custom != null ? custom.getName() : "Customer Deleted");

        }
        return ResponseEntity.ok().body(listBilling);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Billing> findById(@PathVariable Integer id) {
        Billing obj = this.bService.findById(id);

        Technician tech = technicianService.findTechById(obj.getIdTechnician());
        Customer custom = customerService.findCustomerById(obj.getIdCustomer());

        if (custom != null) {
            obj.setNameCustomer(custom.getName());
        } else {
            obj.setNameCustomer("Customer deleted");
        }
        if (tech != null) {
            obj.setNameTechnician(tech.getName());
        } else {
            obj.setNameTechnician("Tech deleted");
        }

        return ResponseEntity.ok().body((obj));

    }

    @PostMapping
    public ResponseEntity<Billing> create(@RequestBody Billing billing) {
        Calculate(billing);
        billing.setTotalValue(totalValue);
        Billing newBilllint = this.bService.create(billing);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newBilllint.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Billing> delete(@PathVariable Integer id) {
        bService.delete(id);
        return ResponseEntity.noContent().build();
    }

    public BigDecimal getCalculatedValueo() {
        return totalValue;
    }

    public void setCalculatedValue(BigDecimal calculatedValue) {
        this.totalValue = calculatedValue;
    }

    public ResponseEntity<BigDecimal> Calculate(@RequestBody Billing billing) {
        totalValue = new BigDecimal(0);
        List<BigDecimal> list = this.sCallRepository.calculate(billing.getIdTechnician(),
                billing.getIdCustomer(),
                billing.getInitialDateBilling(),
                billing.getFinalDateBilling());
        for (BigDecimal item : list) {
            totalValue = totalValue.add(item);
        }
        return ResponseEntity.ok().body(totalValue);
    }

}
