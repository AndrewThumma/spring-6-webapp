package guru.springframework.spring6webapp.bootstrap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import guru.springframework.spring6webapp.repositories.BeerRepository;
import guru.springframework.spring6webapp.repositories.CustomerRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BootstrapDataTest {
    
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    BootstrapData bootstrapData;

    @BeforeEach
    void setUp(){
        bootstrapData = new BootstrapData(beerRepository, customerRepository);
    }
    
    @Test
    void testRun() throws Exception{
        bootstrapData.run();

        assertThat(beerRepository.count()).isEqualTo(3);
        assertThat(customerRepository.count()).isEqualTo(2);
    }
}
