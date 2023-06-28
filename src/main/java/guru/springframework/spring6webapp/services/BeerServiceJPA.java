package guru.springframework.spring6webapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import guru.springframework.spring6webapp.entities.Beer;
import guru.springframework.spring6webapp.mappers.BeerMapper;
import guru.springframework.spring6webapp.model.BeerDTO;
import guru.springframework.spring6webapp.model.BeerStyle;
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
    public List<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory) {
        
        List<Beer> beerList;
        
        if(StringUtils.hasText(beerName) && beerStyle == null){            
            beerList = listBeersByName(beerName);
        }
        else if(!StringUtils.hasText(beerName) && beerStyle != null){
            beerList=listBeersByStyle(beerStyle);
        }
        else if(StringUtils.hasText(beerName) && beerStyle != null){
            beerList=listBeersByNameStyle(beerName, beerStyle);
        }
        else {
            beerList = beerRepository.findAll();
        }

        if (showInventory != null && !showInventory){
            beerList.forEach(beer -> beer.setQuantityOnHand(null));
        }
        
        return beerList.stream()
            .map(beerMapper::beerToBeerDto)
            .collect(Collectors.toList());
    }

    private List<Beer> listBeersByNameStyle(String beerName, BeerStyle beerStyle) {
        return beerRepository.findAllByBeerNameIsLikeIgnoreCaseAndBeerStyle("%" + beerName + "%", beerStyle);
    }

    public List<Beer> listBeersByStyle(BeerStyle beerStyle) {
        return beerRepository.findAllByBeerStyle(beerStyle);
    }

    //support function
    public List<Beer> listBeersByName(String beerName){
        return beerRepository.findAllByBeerNameIsLikeIgnoreCase("%" + beerName + "%");
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
    public Boolean deleteById(UUID beerId) {
        if(beerRepository.existsById(beerId)) {
            beerRepository.deleteById(beerId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer) {
        AtomicReference<Optional<BeerDTO>> atomicReference = new AtomicReference<>();

        beerRepository.findById(beerId).ifPresentOrElse(foundBeer -> {
            if (StringUtils.hasText(beer.getBeerName())){
                foundBeer.setBeerName(beer.getBeerName());
            }
            if (beer.getBeerStyle() != null){
                foundBeer.setBeerStyle(beer.getBeerStyle());
            }
            if (StringUtils.hasText(beer.getUpc())){
                foundBeer.setUpc(beer.getUpc());
            }
            if (beer.getPrice() != null){
                foundBeer.setPrice(beer.getPrice());
            }
            if (beer.getQuantityOnHand() != null){
                foundBeer.setQuantityOnHand(beer.getQuantityOnHand());
            }
            atomicReference.set(Optional.of(beerMapper
                    .beerToBeerDto(beerRepository.save(foundBeer))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }
    
}
