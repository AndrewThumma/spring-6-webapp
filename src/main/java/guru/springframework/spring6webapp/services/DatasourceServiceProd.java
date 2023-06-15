package guru.springframework.spring6webapp.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("Prod")
@Service("datasource")
public class DatasourceServiceProd implements DatasourceService{

    @Override
    public String getDatasource() {
        return "Production";
    }
    
}
