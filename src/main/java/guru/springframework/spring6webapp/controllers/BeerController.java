package guru.springframework.spring6webapp.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.spring6webapp.model.Beer;
import guru.springframework.spring6webapp.services.BeerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class BeerController {
    
    private final BeerService beerService;

    @RequestMapping("/api/v1/beer")
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }

    public Beer getBeerById(UUID id){        

        return beerService.getBeerById(id);
    }


}
