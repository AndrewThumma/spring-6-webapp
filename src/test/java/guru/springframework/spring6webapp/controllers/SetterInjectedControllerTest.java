package guru.springframework.spring6webapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.spring6webapp.services.GreetingServiceImpl;

public class SetterInjectedControllerTest {
    
    SetterInjectedController setterInjectedController;

    @BeforeEach
    void setUp(){
        setterInjectedController = new SetterInjectedController();
        setterInjectedController.setGreetingService(new GreetingServiceImpl());
    }
    
    @Test
    void testSayHello() {
        System.out.println(setterInjectedController.sayHello());
    }
}
