package guru.springframework.spring6webapp.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import guru.springframework.spring6webapp.model.BeerDTO;
import guru.springframework.spring6webapp.model.BeerStyle;

public interface BeerService {
    Optional<BeerDTO> getBeerById(UUID id);
    
    public List<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory);

    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID id, BeerDTO beer);

    Boolean deleteById(UUID beerId);

    Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer);
}
