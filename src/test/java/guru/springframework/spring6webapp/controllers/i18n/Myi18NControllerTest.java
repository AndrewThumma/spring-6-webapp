package guru.springframework.spring6webapp.controllers.i18n;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("ES")
@SpringBootTest
public class Myi18NControllerTest {
    
    @Autowired
    Myi18NController myi18nController;

    @Test
    void testSayHello() {

        System.out.println(myi18nController.sayHello());
    }
}
