package guru.springframework.spring6webapp.mappers;

import org.mapstruct.Mapper;

import guru.springframework.spring6webapp.entities.Customer;
import guru.springframework.spring6webapp.model.CustomerDTO;

@Mapper
public interface CustomerMapper {
    
    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);
}
