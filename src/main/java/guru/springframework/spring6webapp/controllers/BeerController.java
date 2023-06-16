package guru.springframework.spring6webapp.controllers;

import org.springframework.stereotype.Controller;

import guru.springframework.spring6webapp.services.BeerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class BeerController {
    
    private final BeerService beerService;

    
}
