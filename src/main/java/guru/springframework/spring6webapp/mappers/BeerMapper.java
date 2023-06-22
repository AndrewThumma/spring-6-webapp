package guru.springframework.spring6webapp.mappers;

import org.mapstruct.Mapper;

import guru.springframework.spring6webapp.entities.Beer;
import guru.springframework.spring6webapp.model.BeerDTO;

@Mapper
public interface BeerMapper {
    
    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}
