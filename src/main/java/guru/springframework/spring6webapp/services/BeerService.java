package guru.springframework.spring6webapp.services;

import java.util.UUID;

import guru.springframework.spring6webapp.model.Beer;

public interface BeerService {
    Beer getBeerById(UUID id);
}
