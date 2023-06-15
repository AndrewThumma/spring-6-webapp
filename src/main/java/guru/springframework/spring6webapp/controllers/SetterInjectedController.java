package guru.springframework.spring6webapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import guru.springframework.spring6webapp.services.GreetingService;

@Controller
public class SetterInjectedController {    
    
    private GreetingService greetingService;
    
    @Autowired
    public void setGreetingService(GreetingService greetingService) {
        System.out.println("SetterInjectedController.setGreetingService");
        this.greetingService = greetingService;
    }

    public String sayHello(){
        return greetingService.sayGreeting();
    }
    
}
