package guru.springframework.spring6webapp.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import guru.springframework.spring6webapp.model.Customer;

public interface CustomerService {

    Optional<Customer> getCustomerById(UUID id);

    List<Customer> listCustomers();
    
    Customer saveCustomer(Customer customer);

    void updateCustomerById(UUID customerId, Customer customer);

    void deleteById(UUID customerId);
}
