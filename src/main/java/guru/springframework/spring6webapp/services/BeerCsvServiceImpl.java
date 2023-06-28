package guru.springframework.spring6webapp.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import guru.springframework.spring6webapp.model.BeerCSVRecord;

public class BeerCsvServiceImpl implements BeerCsvService{

    @Override
    public List<BeerCSVRecord> convertCSV(File csvFile) {
        
        try{
            List<BeerCSVRecord> records = new CsvToBeanBuilder<BeerCSVRecord>(new FileReader(csvFile))
            .withType(BeerCSVRecord.class)
            .build().parse();
        return records;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        

    }
    
}
