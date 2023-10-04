package com.api.customer.respositories;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.customer.entities.ServiceCall;

public interface ServiceCallRepository extends JpaRepository<ServiceCall, Integer> {

    boolean existsByIdCustomer(Long idCustomer);

    @Query("SELECT sc.value FROM ServiceCall sc " +
            "WHERE sc.idTechnician = :idTechnician " +
            "AND sc.idCustomer = :idCustomer " +
            "AND sc.openingDate >= :initialDateBilling " +
            "AND sc.openingDate <= :finalDateBilling")
    List<BigDecimal> calculate(
            @Param("idTechnician") Long idTechnician,
            @Param("idCustomer") Long idCustomer,
            @Param("initialDateBilling") Date initialDateBilling,
            @Param("finalDateBilling") Date finalDateBilling);

}
