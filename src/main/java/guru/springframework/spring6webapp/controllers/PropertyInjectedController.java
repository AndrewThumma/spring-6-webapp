package guru.springframework.spring6webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import guru.springframework.spring6webapp.services.GreetingService;

@Controller
public class PropertyInjectedController {
    
    @Autowired
    GreetingService greetingService;

    public String sayHello(){
        return greetingService.sayGreeting();
    }
}
