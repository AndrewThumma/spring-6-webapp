package guru.springframework.spring6webapp.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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
        return Optional.ofNullable(beerMapper.beerToBeerDto(beerRepository.findById(id).orElse(null)));
    }

    @Override
    public List<BeerDTO> listBeers() {
        return beerRepository.findAll()
            .stream()
            .map(beerMapper::beerToBeerDto)
            .collect(Collectors.toList());
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beer)));
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID id, BeerDTO beer) {
        AtomicReference<Optional<BeerDTO>> attAtomicReference = new AtomicReference<>();
        
        beerRepository.findById(id).ifPresentOrElse(foundbBeer -> {
            foundbBeer.setBeerName(beer.getBeerName());
            foundbBeer.setBeerStyle(beer.getBeerStyle());
            foundbBeer.setUpc(beer.getUpc());
            foundbBeer.setPrice(beer.getPrice());
            attAtomicReference.set(Optional.of(beerMapper.beerToBeerDto(beerRepository.save(foundbBeer))));
        }, () -> {
            attAtomicReference.set(Optional.empty());
        });

        return attAtomicReference.get();

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
