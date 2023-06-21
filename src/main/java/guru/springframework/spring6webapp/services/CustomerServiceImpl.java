package guru.springframework.spring6webapp.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.spring6webapp.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

    private Map<UUID, Customer> customerMap;

    public CustomerServiceImpl(){
        this.customerMap = new HashMap<>();

        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Andrew Thumma")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .version("1")
                .build();

        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Jameson Thumma")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .version("1")
                .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
    }

    @Override
    public Optional<Customer> getCustomerById(UUID id) {
        return Optional.of(customerMap.get(id));
    }

    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        
        Customer savedCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .version("1")
                .customerName(customer.getCustomerName())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(savedCustomer.getId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public void updateCustomerById(UUID customerId, Customer customer){
        Customer updatedCustomer = customerMap.get(customerId);

        updatedCustomer.setCustomerName(customer.getCustomerName());
        updatedCustomer.setVersion(customer.getVersion());
        updatedCustomer.setLastModifiedDate(LocalDateTime.now());

        customerMap.put(updatedCustomer.getId(), updatedCustomer);
    }

    @Override
    public void deleteById(UUID customerId) {
        
        customerMap.remove(customerId);
    }
    
}
