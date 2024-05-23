package ChartAndHistory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@DataJpaTest
@ContextConfiguration
//@SpringBootTest
class CartAndHistoryApplicationTests {

    @Test
    void contextLoads() {
    }

}
