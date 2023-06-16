package guru.springframework.spring6webapp.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.spring6webapp.model.Beer;
import guru.springframework.spring6webapp.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService{

    @Override
    public Beer getBeerById(UUID id) {
        
        log.debug("Get Beer Id in service was called");
        
        return Beer.builder()
                .id(id)
                .version(1)
                .beerName("GalaxyCat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123456")
                .price(new BigDecimal(12.99))
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
    
}
