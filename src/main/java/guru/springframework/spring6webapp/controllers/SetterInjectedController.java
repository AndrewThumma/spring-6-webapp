package guru.springframework.spring6webapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import guru.springframework.spring6webapp.services.GreetingService;

@Controller
public class SetterInjectedController {
    
    @Autowired
    private GreetingService greetingService;
    
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHello(){
        return greetingService.sayGreeting();
    }
    
}
