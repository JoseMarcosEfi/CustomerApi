package com.api.customer.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.api.customer.enums.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private Integer id;
    private String description;
    private Integer amount;
    private BigDecimal valueBuy;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate purchaseDate = LocalDate.now();

    private Type type;

}
