package guru.springframework.spring6webapp.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import guru.springframework.spring6webapp.mappers.CustomerMapper;
import guru.springframework.spring6webapp.model.CustomerDTO;
import guru.springframework.spring6webapp.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService{
    
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public Optional<CustomerDTO> getCustomerById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCustomerById'");
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listCustomers'");
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveCustomer'");
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCustomerById'");
    }

    @Override
    public void deleteById(UUID customerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
    
}
