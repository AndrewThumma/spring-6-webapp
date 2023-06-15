package guru.springframework.spring6webapp.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"dev", "default"})
@Service("datasource")
public class DatasourceServiceDev implements DatasourceService{

    @Override
    public String getDatasource() {
       return "Dev";
    }
    
}
