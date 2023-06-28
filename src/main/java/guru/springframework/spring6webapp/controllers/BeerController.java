package guru.springframework.spring6webapp.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.spring6webapp.model.BeerDTO;
import guru.springframework.spring6webapp.services.BeerService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";
    
    private final BeerService beerService;

    @GetMapping(BEER_PATH)
    public List<BeerDTO> listBeers(@RequestParam(required = false) String beerName){
        return beerService.listBeers();
    }

    @GetMapping(BEER_PATH_ID)
    public BeerDTO getBeerById(@PathVariable("beerId") UUID id){        
        return beerService.getBeerById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity<String> handlePost(@Validated @RequestBody BeerDTO beer){

        BeerDTO savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", BEER_PATH + "/" + savedBeer.getId().toString());

        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

     @PutMapping(BEER_PATH_ID)
    public ResponseEntity<String> updateById(@PathVariable UUID beerId,@Validated @RequestBody BeerDTO beer){

        if(beerService.updateBeerById(beerId, beer).isEmpty()){
            throw new NotFoundException();
        };

        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity<String> deleteById(@PathVariable UUID beerId){

        if(!beerService.deleteById(beerId)){
            throw new NotFoundException();
        }

        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity<String> updateBeerPatchId(@PathVariable UUID beerId, @RequestBody BeerDTO beer){

        beerService.patchBeerById(beerId, beer);

        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

}
