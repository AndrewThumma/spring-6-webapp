package guru.springframework.spring6webapp.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import guru.springframework.spring6webapp.entities.Beer;
import guru.springframework.spring6webapp.mappers.BeerMapper;
import guru.springframework.spring6webapp.model.BeerDTO;
import guru.springframework.spring6webapp.repositories.BeerRepository;
import jakarta.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class BeerControllerIT {
    
    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;

    @Rollback
    @Transactional
    @Test
    void testDeleteById() {
        Beer beer = beerRepository.findAll().get(0);

        ResponseEntity responseEntity = beerController.deleteById(beer.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
        assertThat(beerRepository.findById(beer.getId())).isEmpty();
    }

    @Test
    void testDeleteNotFound(){
        assertThrows(NotFoundException.class, () -> {
            beerController.deleteById(UUID.randomUUID());
        });
    }

    @Test
    void testGetBeerById() {
        Beer beer = beerRepository.findAll().get(0);

        BeerDTO dto = beerController.getBeerById(beer.getId());

        assertThat(dto).isNotNull();
    }

    @Test
    void testBeerIdNotFound(){
        assertThrows(NotFoundException.class, () ->{
            beerController.getBeerById(UUID.randomUUID());
        });
        
        
    }

    @Rollback
    @Transactional
    @Test
    void testHandlePost() {
        BeerDTO beerDto = BeerDTO.builder()
            .beerName("New Beer")
            .build();

        ResponseEntity responseEntity = beerController.handlePost(beerDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[3]);

        Beer beer = beerRepository.findById(savedUUID).get();

        assertThat(beer).isNotNull();
    }

    @Test
    void testListBeers() {
        List<BeerDTO> dtos = beerController.listBeers();

        assertThat(dtos.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList(){
        beerRepository.deleteAll();
        List<BeerDTO> dtos = beerController.listBeers();

        assertThat(dtos.size()).isEqualTo(0);
    }

    @Test
    void testUpdateBeerPatchId() {

    }

    @Rollback
    @Transactional
    @Test
    void testUpdateById() {
        Beer beer = beerRepository.findAll().get(0);

        BeerDTO dto = beerMapper.beerToBeerDto(beer);
        dto.setId(null);
        dto.setVersion(null);
        final String beerName = "UPDATED";
        dto.setBeerName(beerName);

        ResponseEntity responseEntity = beerController.updateById(beer.getId(), dto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Beer updatedBeer = beerRepository.findById(beer.getId()).get();
        assertThat(updatedBeer.getBeerName()).isEqualTo(beerName);
    }

    @Test
    void testUpdateNotFound(){
        assertThrows(NotFoundException.class, () -> {
            beerController.updateById(UUID.randomUUID(), BeerDTO.builder().build());
        });
    }
}
