package guru.springframework.spring6webapp.services.i18n;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.spring6webapp.services.GreetingService;

@Profile("ES")
@Service("i18NService")
public class SpanisGreetingService implements GreetingService{

    @Override
    public String sayGreeting() {
        return "Hola Mundo - ES";
    }
    
}
