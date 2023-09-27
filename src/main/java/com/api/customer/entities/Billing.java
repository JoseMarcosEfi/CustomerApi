package com.api.customer.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

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
    private Integer idTechnician;
    private Integer idCustomer;
    private Date initialDateBilling;
    private Date finalDateBilling;
    @Transient
    private String nameCustomer;
    @Transient
    private String nameTechnician;
}
