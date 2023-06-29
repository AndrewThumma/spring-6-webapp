package guru.springframework.spring6webapp.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        return Optional.ofNullable(beerMapper.beerToBeerDto(beerRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize) {            
        
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
        
        Page<Beer> beerPage;
        
        if(StringUtils.hasText(beerName) && beerStyle == null){            
            beerPage = listBeersByName(beerName, pageRequest);
        }
        else if(!StringUtils.hasText(beerName) && beerStyle != null){
            beerPage=listBeersByStyle(beerStyle, pageRequest);
        }
        else if(StringUtils.hasText(beerName) && beerStyle != null){
            beerPage=listBeersByNameStyle(beerName, beerStyle, pageRequest);
        }
        else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        if (showInventory != null && !showInventory){
            beerPage.forEach(beer -> beer.setQuantityOnHand(null));
        }
        
        return beerPage.map(beerMapper::beerToBeerDto);
        //return beerPage.stream()
        //        .map(beerMapper::beerToBeerDto)
        //        .collect(Collectors.toList());
    }

    public PageRequest buildPageRequest(Integer pageNumber, Integer pageSize){
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > 0){
            queryPageNumber = pageNumber -1;
        }else {
            queryPageNumber = DEFAULT_PAGE;
        }

        if (pageSize == null) {            
            queryPageSize = DEFAULT_PAGE_SIZE;
        }else {
            if (pageSize > 1000){
                queryPageSize = 1000;
            } else{
                queryPageSize = pageSize;
            }            
        }

        return PageRequest.of(queryPageNumber, queryPageSize);

    }

    public Page<Beer> listBeersByNameStyle(String beerName, BeerStyle beerStyle, PageRequest pageRequest) {
        return beerRepository.findAllByBeerNameIsLikeIgnoreCaseAndBeerStyle("%" + beerName + "%", beerStyle, pageRequest);
    }

    public Page<Beer> listBeersByStyle(BeerStyle beerStyle, PageRequest pageRequest) {
        return beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
    }

    //support function
    public Page<Beer> listBeersByName(String beerName, PageRequest pageRequest){
        return beerRepository.findAllByBeerNameIsLikeIgnoreCase("%" + beerName + "%", pageRequest);
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
