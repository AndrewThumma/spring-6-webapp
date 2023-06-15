package guru.springframework.spring6webapp.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import guru.springframework.spring6webapp.services.DatasourceService;

@Controller
public class DatasourceController {

    private final DatasourceService datasourceService;

    public DatasourceController(@Qualifier("datasource") DatasourceService datasourceService) {
        this.datasourceService = datasourceService;
    }

    public String sayDatasource(){
        return datasourceService.getDatasource();
    }
    
}
