package guru.springframework.spring6webapp.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"UAT", "EN"})
@SpringBootTest
public class DatasourceControllerTestUAT {
    
    @Autowired
    DatasourceController datasourceController;
    
    @Test
    void testSayDatasource() {
        System.out.println(datasourceController.sayDatasource());
    }
}
