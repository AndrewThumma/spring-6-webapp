package guru.springframework.spring6webapp.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import guru.springframework.spring6webapp.bootstrap.BootstrapData;
import guru.springframework.spring6webapp.entities.Beer;
import guru.springframework.spring6webapp.model.BeerStyle;
import guru.springframework.spring6webapp.services.BeerCsvServiceImpl;
import jakarta.validation.ConstraintViolationException;

@DataJpaTest
@Import({BootstrapData.class, BeerCsvServiceImpl.class})
public class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testGetBeerListByName(){
        List<Beer> list = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%");

        assertThat(list.size()).isEqualTo(336);
    }

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
            .beerName("My Beer")
            .beerStyle(BeerStyle.PALE_ALE)
            .upc("123456789")
            .price(new BigDecimal("11.00"))
            .build());

        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }

    @Test
    void testSaveBeerNameTooLong() {
        
        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
            .beerName("My BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy Beer")
            .beerStyle(BeerStyle.PALE_ALE)
            .upc("123456789")
            .price(new BigDecimal("11.00"))
            .build());

             beerRepository.flush();
        });        
    }
}
