package guru.springframework.spring6webapp.services;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import guru.springframework.spring6webapp.model.BeerCSVRecord;

import static org.assertj.core.api.Assertions.assertThat;

public class BeerCsvServiceImplTest {
    
    BeerCsvService beerCsvService = new BeerCsvServiceImpl();

    @Test
    void convertCSV() throws Exception{
        File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");

        List<BeerCSVRecord> recs = beerCsvService.convertCSV(file);

        System.out.println(recs.size());

        assertThat(recs.size()).isGreaterThan(0);
    }
}
