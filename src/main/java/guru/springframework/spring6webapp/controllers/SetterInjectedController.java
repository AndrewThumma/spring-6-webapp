package guru.springframework.spring6webapp.controllers;


import guru.springframework.spring6webapp.services.GreetingService;

public class SetterInjectedController {
    private GreetingService greetingService;

    

    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHello(){
        return greetingService.sayGreeting();
    }
    
}
