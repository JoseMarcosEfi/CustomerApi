package com.api.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.customer.entities.Technician;
import com.api.customer.services.TechnicianService;

@RestController
@RequestMapping("/technician")
@CrossOrigin(value = "*")
public class TechnicianController {
    @Autowired
    private TechnicianService tService;

    @GetMapping
    public ResponseEntity<List<Technician>> getAlltechs() {
        List<Technician> techs = tService.listAllTechs();
        if (techs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(techs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technician> getTechById(@PathVariable(value = "id") Long id) {
        Technician tech = tService.findTechById(id);
        if (tech != null) {
            return ResponseEntity.ok().body(tech);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Technician> postTech(@Validated @RequestBody Technician tech) {
        Technician newTech = tService.createTech(tech);
        return new ResponseEntity<>(newTech, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Technician> putTech(@PathVariable Long id,
            @Validated @RequestBody Technician tech) {
        tech = tService.changeTech(id, tech);
        if (tech != null) {
            return ResponseEntity.ok().body(tech);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTech(@PathVariable Long id) {
        tService.deleteTech(id);
        return ResponseEntity.noContent().build();
    }

}
