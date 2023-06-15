package guru.springframework.spring6webapp.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("UAT")
@Service("datasource")
public class DatasourceServiceUAT implements DatasourceService{

    @Override
    public String getDatasource() {
        return "UAT";
    }
    
}
