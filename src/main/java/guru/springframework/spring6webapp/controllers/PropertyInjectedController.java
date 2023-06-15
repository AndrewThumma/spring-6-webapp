package guru.springframework.spring6webapp.controllers;

import guru.springframework.spring6webapp.services.GreetingService;

public class PropertyInjectedController {
    
    GreetingService greetingService;

    public String sayHello(){
        return greetingService.sayGreeting();
    }
}
