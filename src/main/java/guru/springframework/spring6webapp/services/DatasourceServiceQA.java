package guru.springframework.spring6webapp.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("QA")
@Service("datasource")
public class DatasourceServiceQA implements DatasourceService{

    @Override
    public String getDatasource() {
        return "QA";
    }
    
}
