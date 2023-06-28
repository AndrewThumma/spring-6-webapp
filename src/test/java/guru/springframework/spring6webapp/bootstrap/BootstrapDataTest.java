package guru.springframework.spring6webapp.bootstrap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import guru.springframework.spring6webapp.repositories.BeerRepository;
import guru.springframework.spring6webapp.repositories.CustomerRepository;
import guru.springframework.spring6webapp.services.BeerCsvService;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(BeerCsvService.class)
public class BootstrapDataTest {
    
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BeerCsvService beerCsvService;

    BootstrapData bootstrapData;

    @BeforeEach
    void setUp(){
        bootstrapData = new BootstrapData(beerRepository, customerRepository, beerCsvService);
    }
    
    @Test
    void testRun() throws Exception{
        bootstrapData.run();

        assertThat(beerRepository.count()).isGreaterThan(0);
        assertThat(customerRepository.count()).isEqualTo(2);
    }
}
