package com.api.customer.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.api.customer.enums.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Integer amount;
    private BigDecimal valueBuy;
    private Type type;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate purchaseDate = LocalDate.now();

}
