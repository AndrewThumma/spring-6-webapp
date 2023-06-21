package guru.springframework.spring6webapp.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.spring6webapp.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID>{
    
}
