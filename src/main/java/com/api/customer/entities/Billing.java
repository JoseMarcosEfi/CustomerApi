package com.api.customer.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Billing implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal totalValue;
    private Long idTechnician;
    private Long idCustomer;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate initialDateBilling;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate finalDateBilling;
    @Transient
    private String nameCustomer;
    @Transient
    private String nameTechnician;
}
