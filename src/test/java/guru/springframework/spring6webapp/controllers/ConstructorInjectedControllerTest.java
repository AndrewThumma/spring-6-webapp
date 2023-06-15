package guru.springframework.spring6webapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.spring6webapp.services.GreetingServiceImpl;

public class ConstructorInjectedControllerTest {
    
    ConstructorInjectedController controller;

    @BeforeEach
    void setUp(){
        controller = new ConstructorInjectedController(new GreetingServiceImpl());
    }
    
    @Test
    void testSayHello() {
        System.out.println(controller.sayHello());
    }
}
