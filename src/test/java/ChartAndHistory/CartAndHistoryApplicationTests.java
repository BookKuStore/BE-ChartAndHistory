package ChartAndHistory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ActiveProfiles("test")
@DataJpaTest
@ContextConfiguration
@EnableTransactionManagement
//@SpringBootTest
class CartAndHistoryApplicationTests {

    @Test
    void contextLoads() {
    }

}
