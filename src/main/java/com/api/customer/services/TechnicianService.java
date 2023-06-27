package com.api.customer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.customer.entities.Technician;
import com.api.customer.respositories.TechnicianRepository;

@Service
public class TechnicianService {
    private TechnicianRepository techRepo;

    public Technician createTech(Technician tech) {
        return techRepo.save(tech);
    }

    public List<Technician> listAllTechs(int id) {
        return techRepo.findAll();
    }

    public Technician findById(int id) {
        Optional<Technician> optionalTech = techRepo.findById(id);
        return optionalTech.orElse(null);
    }

    public Technician changeTech(int id, Technician tech) {
        Optional<Technician> optionalTech = techRepo.findById(id);
        if (optionalTech.isPresent()) {
            tech.setId(id);
            return techRepo.save(tech);
        }
        return null;
    }

    public void deleteTech(int id) {
        techRepo.deleteById(id);
    }

}
