package guru.springframework.spring6webapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.spring6webapp.services.GreetingServiceImpl;

public class PropertyInjectedControllerTest {
   
   PropertyInjectedController propertyInjectedController;

   @BeforeEach
   void setUp(){
        propertyInjectedController = new PropertyInjectedController();
        propertyInjectedController.greetingService = new GreetingServiceImpl();
   }
   
    @Test
    void testSayHello() {
        System.out.println(propertyInjectedController.sayHello());
    }
}
