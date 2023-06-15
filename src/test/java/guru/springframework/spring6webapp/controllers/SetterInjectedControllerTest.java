package guru.springframework.spring6webapp.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SetterInjectedControllerTest {
    
    @Autowired
    SetterInjectedController setterInjectedController;
    
    @Test
    void testSayHello() {
        System.out.println(setterInjectedController.sayHello());
    }
}
