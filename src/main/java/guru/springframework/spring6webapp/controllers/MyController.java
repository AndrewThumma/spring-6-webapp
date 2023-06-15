package guru.springframework.spring6webapp.controllers;

import org.springframework.stereotype.Controller;

import guru.springframework.spring6webapp.services.GreetingService;
import guru.springframework.spring6webapp.services.GreetingServiceImpl;

@Controller
public class MyController {

    private final GreetingService greetingService;

    
    
    public MyController() {
        this.greetingService = new GreetingServiceImpl();
    }



    public String sayHello(){
        System.out.println("I'm in the controller");

        return greetingService.sayGreeting();
    }
}
