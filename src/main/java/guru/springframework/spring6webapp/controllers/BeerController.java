package guru.springframework.spring6webapp.controllers;

import java.util.UUID;

import org.springframework.stereotype.Controller;

import guru.springframework.spring6webapp.model.Beer;
import guru.springframework.spring6webapp.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Controller
public class BeerController {
    
    private final BeerService beerService;

    public Beer getBeerById(UUID id){

        log.debug("Get Beer by Id - in controller");

        return beerService.getBeerById(id);
    }


}
