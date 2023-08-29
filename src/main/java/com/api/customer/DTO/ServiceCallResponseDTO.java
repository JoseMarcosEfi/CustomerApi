package com.api.customer.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.api.customer.enums.Priority;
import com.api.customer.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceCallResponseDTO {
    private Integer id;
    private Priority priority;
    private Status status;
    private String observations;
    private String title;
    private BigDecimal value;
    private Long idTechnician;
    private Long idCustomer;
    private LocalDate closingDate;

}
