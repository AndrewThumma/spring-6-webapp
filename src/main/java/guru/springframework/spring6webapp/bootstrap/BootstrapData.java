package guru.springframework.spring6webapp.bootstrap;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring6webapp.repositories.BeerRepository;
import guru.springframework.spring6webapp.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import guru.springframework.spring6webapp.entities.Beer;
import guru.springframework.spring6webapp.entities.Customer;
import guru.springframework.spring6webapp.model.BeerStyle;

@RequiredArgsConstructor   
@Component
public class BootstrapData implements CommandLineRunner{
    
    private final BeerRepository beerRepository;    
    private final CustomerRepository customerRepository;
    
    
    @Override
    public void run(String... args) throws Exception {
        loadBeerData();
        loadCustomerData();
                        
    }


    private void loadCustomerData() {
        
        if(customerRepository.count() == 0){
        
            Customer customer1 = Customer.builder()
                    .customerName("Andrew Thumma")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            Customer customer2 = Customer.builder()
                    .customerName("Jameson Thumma")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            customerRepository.saveAll(Arrays.asList(customer1, customer2));    
        }       
    }


    private void loadBeerData() {

        if (beerRepository.count() == 0){

            Beer beer1 = Beer.builder()                                
                    .beerName("Galacy Cat")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("12356")
                    .price(new BigDecimal("12.99"))
                    .quantityOnHand(122)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();


            Beer beer2 = Beer.builder()
                    .beerName("Crank")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("12356222")
                    .price(new BigDecimal("11.99"))
                    .quantityOnHand(392)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

            Beer beer3 = Beer.builder()
                    .beerName("Sunshine City")
                    .beerStyle(BeerStyle.IPA)
                    .upc("12356")
                    .price(new BigDecimal("13.99"))
                    .quantityOnHand(144)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

            beerRepository.save(beer1);
            beerRepository.save(beer2);
            beerRepository.save(beer3); 
        }
    }


}
