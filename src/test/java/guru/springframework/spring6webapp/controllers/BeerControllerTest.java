package guru.springframework.spring6webapp.controllers;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BeerControllerTest {
    
    @Autowired
    BeerController myBeerController;
    
    
    @Test
    void testGetBeerById() {
        System.out.println(myBeerController.getBeerById(UUID.randomUUID()));
    }
}
