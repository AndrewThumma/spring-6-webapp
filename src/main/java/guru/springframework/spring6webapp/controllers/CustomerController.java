package guru.springframework.spring6webapp.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.spring6webapp.model.CustomerDTO;
import guru.springframework.spring6webapp.services.CustomerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";
    
    private final CustomerService customerService;

    @GetMapping(CUSTOMER_PATH)
    public List<CustomerDTO> listCustomers(){
        return customerService.listCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    public CustomerDTO getCustomerById(@PathVariable("customerId") UUID id){
        return customerService.getCustomerById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customer){

        CustomerDTO savedCustomer = customerService.saveCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", CUSTOMER_PATH + "/" + savedCustomer.getId().toString());

        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

   @PutMapping(CUSTOMER_PATH_ID)
   public ResponseEntity<String> updateCustomerById(@PathVariable UUID customerId, @RequestBody CustomerDTO customer){

        if(customerService.updateCustomerById(customerId, customer).isEmpty()){
            throw new NotFoundException();
        }
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        
   }

   @DeleteMapping(CUSTOMER_PATH_ID)
   public ResponseEntity<String> deleteById(@PathVariable UUID customerId){
        
        if(!customerService.deleteById(customerId)){
            throw new NotFoundException();
        }
        
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);                
   }
}
