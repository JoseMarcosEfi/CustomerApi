package com.api.customer.services;

import java.util.List;
import java.util.Optional;

import com.api.customer.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.customer.entities.Technician;
import com.api.customer.respositories.TechnicianRepository;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository techRepo;

    public Technician createTech(Technician tech) {
        return techRepo.save(tech);
    }

    public List<Technician> listAllTechs() {
        return techRepo.findAll();
    }

    public Technician findTechById(Long id) {
        Optional<Technician> optionalTech = techRepo.findById(id);
        return optionalTech.orElse(null);
    }

    public Technician changeTech(Long id, Technician tech) {
        Optional<Technician> optionalTech = techRepo.findById(id);
        if (optionalTech.isPresent()) {
            tech.setId(id);
            return techRepo.save(tech);
        }
        return null;
    }

    public void deleteTech(Long id) {
        techRepo.deleteById(id);
    }

}
