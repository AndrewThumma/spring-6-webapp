package guru.springframework.spring6webapp.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import guru.springframework.spring6webapp.model.Customer;
import guru.springframework.spring6webapp.services.CustomerService;
import guru.springframework.spring6webapp.services.CustomerServiceImpl;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.UUID;


@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    
    @Autowired
    MockMvc mvc;

    @MockBean
    CustomerService customerService;

    CustomerServiceImpl service = new CustomerServiceImpl();
    
    
    @Test
    void testGetCustomerById() throws Exception{
        Customer testCustomer = service.listCustomers().get(0);

        given(customerService.getCustomerById(testCustomer.getId())).willReturn(testCustomer);

        mvc.perform(get(CustomerController.CUSTOMER_PATH_ID, testCustomer.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(testCustomer.getId().toString())))
            .andExpect(jsonPath("$.customerName", is(testCustomer.getCustomerName())));
    }

    @Test
    void getBeerByIdNotFound() throws Exception{
        
        given(customerService.getCustomerById(any(UUID.class))).willThrow(NotFoundException.class);
        
        mvc.perform(get(BeerController.BEER_PATH_ID, UUID.randomUUID()))        
            .andExpect(status().isNotFound());
    }

    @Test
    void testListCustomers() throws Exception{
        given(customerService.listCustomers()).willReturn(service.listCustomers());

        mvc.perform(get(CustomerController.CUSTOMER_PATH)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()", is(2)));

    }
}
