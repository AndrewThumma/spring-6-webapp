package guru.springframework.spring6webapp.controllers;


import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import guru.springframework.spring6webapp.entities.Beer;
import guru.springframework.spring6webapp.entities.Customer;
import guru.springframework.spring6webapp.mappers.CustomerMapper;
import guru.springframework.spring6webapp.model.BeerDTO;
import guru.springframework.spring6webapp.model.CustomerDTO;
import guru.springframework.spring6webapp.repositories.BeerRepository;
import guru.springframework.spring6webapp.repositories.CustomerRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CustomerControllerIT {
    
    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    CustomerMapper customerMapper;
    
    @Rollback
    @Transactional
    @Test
    void testCreateCustomer() {
        CustomerDTO dto = CustomerDTO.builder()
            .customerName("New Customer")
            .build();

        ResponseEntity responseEntity = customerController.createCustomer(dto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[3]);

        Customer customer = customerRepository.findById(savedUUID).get();

        assertThat(customer).isNotNull();
    }

    @Rollback
    @Transactional
    @Test
    void testDeleteById() {
        Customer customer = customerRepository.findAll().get(0);

        ResponseEntity responseEntity = customerController.deleteById(customer.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
        assertThat(customerRepository.findById(customer.getId())).isEmpty();
    }

    @Test
    void testDeleteNotFound(){
        assertThrows(NotFoundException.class, () -> {
            customerController.deleteById(UUID.randomUUID());
        });
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

    @Rollback
    @Transactional
    @Test
    void testUpdateCustomerById() {
        Customer customer = customerRepository.findAll().get(0);

        CustomerDTO dto = customerMapper.customerToCustomerDto(customer);
        dto.setId(null);
        dto.setVersion(null);
        final String customerName = "Frank";
        dto.setCustomerName(customerName);

        ResponseEntity responseEntity = customerController.updateCustomerById(customer.getId(), dto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer updated = customerRepository.findById(customer.getId()).get();
        assertThat(updated.getCustomerName()).isEqualTo(customerName);
    }

    @Test
    void testUpdateNotFound(){
        assertThrows(NotFoundException.class, () -> {
            customerController.updateCustomerById(UUID.randomUUID(), CustomerDTO.builder().build());
        });        
    }
}
