package guru.springframework.spring6webapp.controllers;


import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import guru.springframework.spring6webapp.entities.Customer;
import guru.springframework.spring6webapp.model.CustomerDTO;
import guru.springframework.spring6webapp.repositories.CustomerRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CustomerControllerIT {
    
    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;
    
    @Test
    void testCreateCustomer() {

    }

    @Test
    void testDeleteById() {

    }

    @Test
    void testGetCustomerById() {
        Customer customer = customerRepository.findAll().get(0);

        CustomerDTO dto = customerController.getCustomerById(customer.getId());

        assertThat(dto).isNotNull();
    }

    @Test
    void testNullId(){
        assertThrows(NotFoundException.class, () -> {
            customerController.getCustomerById(UUID.randomUUID());
        });
          
    }

    @Test
    void testListCustomers() {
        List<CustomerDTO> dtos = customerController.listCustomers();

        assertThat(dtos.size()).isEqualTo(2);
    }

    @Rollback
    @Transactional
    @Test  
    void testEmptyList(){
        customerRepository.deleteAll();

        List<CustomerDTO> dtos = customerController.listCustomers();

        assertThat(dtos.size()).isEqualTo(0);
    }

    @Test
    void testUpdateCustomerById() {

    }
}
