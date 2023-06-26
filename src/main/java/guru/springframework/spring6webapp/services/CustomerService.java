package guru.springframework.spring6webapp.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import guru.springframework.spring6webapp.model.CustomerDTO;

public interface CustomerService {

    Optional<CustomerDTO> getCustomerById(UUID id);

    List<CustomerDTO> listCustomers();
    
    CustomerDTO saveCustomer(CustomerDTO customer);

    Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer);

    Boolean deleteById(UUID customerId);
}
