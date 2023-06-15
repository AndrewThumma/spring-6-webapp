package guru.springframework.spring6webapp.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"QA", "EN"})
@SpringBootTest
public class DatasourceControllerTestQA {
    
    @Autowired
    DatasourceController datasourceController;
    
    @Test
    void testSayDatasource() {
        System.out.println(datasourceController.sayDatasource());
    }
}
