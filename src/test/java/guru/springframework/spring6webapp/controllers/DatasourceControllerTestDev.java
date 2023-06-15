package guru.springframework.spring6webapp.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DatasourceControllerTestDev {
    
    @Autowired
    DatasourceController datasourceController;
    
    @Test
    void testSayDatasource() {
        System.out.println(datasourceController.sayDatasource());
    }
}
