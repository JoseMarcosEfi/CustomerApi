package com.api.customer.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.api.customer.enums.Priority;
import com.api.customer.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Priority priority;
    private Status status;

    private String observations;
    private String title;
    private BigDecimal value;
    private Long idTechnician;
    private Long idCustomer;
    @Transient
    private String nameTechnician;
    @Transient
    private String nameCustomer;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openingDate = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closingDate;

}
