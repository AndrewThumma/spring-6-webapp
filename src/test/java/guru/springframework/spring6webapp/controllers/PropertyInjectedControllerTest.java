package guru.springframework.spring6webapp.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PropertyInjectedControllerTest {
   
    @Autowired
    PropertyInjectedController propertyInjectedController;


   
    @Test
    void testSayHello() {
        System.out.println(propertyInjectedController.sayHello());
    }
}
