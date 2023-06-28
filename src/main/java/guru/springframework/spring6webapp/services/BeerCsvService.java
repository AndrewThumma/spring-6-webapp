package guru.springframework.spring6webapp.services;

import java.io.File;
import java.util.List;

import guru.springframework.spring6webapp.model.BeerCSVRecord;

public interface BeerCsvService {
    List<BeerCSVRecord> convertCSV(File csvFile);
}
