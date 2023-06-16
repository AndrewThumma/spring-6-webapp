package guru.springframework.spring6webapp.services;

import java.util.List;
import java.util.UUID;

import guru.springframework.spring6webapp.model.Beer;

public interface BeerService {
    Beer getBeerById(UUID id);
    
    public List<Beer> listBeers();

    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID id, Beer beer);
}
