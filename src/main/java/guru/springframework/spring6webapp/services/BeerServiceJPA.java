package guru.springframework.spring6webapp.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import guru.springframework.spring6webapp.mappers.BeerMapper;
import guru.springframework.spring6webapp.model.BeerDTO;
import guru.springframework.spring6webapp.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService{

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBeerById'");
    }

    @Override
    public List<BeerDTO> listBeers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listBeers'");
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveNewBeer'");
    }

    @Override
    public void updateBeerById(UUID id, BeerDTO beer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBeerById'");
    }

    @Override
    public void deleteById(UUID beerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public void patchBeerById(UUID beerId, BeerDTO beer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patchBeerById'");
    }
    
}
