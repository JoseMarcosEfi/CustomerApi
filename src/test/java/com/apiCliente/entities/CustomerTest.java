package com.apiCliente.entities;

import com.api.customer.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerTest {
    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
    }

    @Test
    public void testId() {
        customer.setId(1L);
        assertEquals(1L, customer.getId());
    }

    @Test
    public void testName() {
        customer.setName("John Doe");
        assertEquals("John Doe", customer.getName());
    }

    @Test
    public void testCpf() {
        // Defina o CPF e verifique se ele é obtido corretamente
        customer.setCpf("123.456.789-00");
        assertEquals("123.456.789-00", customer.getCpf());
    }

    // Adicione mais testes para outros atributos, se necessário

    @Test
    public void testCreationDate() {
        assertNotNull(customer.getCreationDate());
    }


}
