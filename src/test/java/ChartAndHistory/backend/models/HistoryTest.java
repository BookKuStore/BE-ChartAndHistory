package ChartAndHistory.backend.models;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.History;
import ChartAndHistory.backend.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

public class HistoryTest {

    private History history;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        history = new History();
    }

//    @Test
//    public void testSaveAndRestore() {
//        // Create some sample data
//        Cart cart1 = new Cart();
//        Cart cart2 = new Cart();
//
//        Product product1 = new Product();
//        product1.setProductName("Product 1");
//        product1.setPrice(10.0);
//
//        Product product2 = new Product();
//        product2.setProductName("Product 2");
//        product2.setPrice(20.0);
//
//        cart1.addProduct(product1);
//        cart1.addProduct(product2);
//
//        List<Cart> paidCheckouts = new ArrayList<>();
//        paidCheckouts.add(cart1);
//        paidCheckouts.add(cart2);
//
//        // Save the state
//        History.HistoryMemento memento = history.save();
//
//        // Restore the state
//        history.restore(memento);
//
//        // Ensure the restored state is not null
//        assertNotNull(history.getPaidCheckouts());
//        // Ensure the size of restored state matches the original size
//        assertEquals(paidCheckouts.size(), history.getPaidCheckouts().size());
//        // Ensure the content of restored state matches the original content
//        for (int i = 0; i < paidCheckouts.size(); i++) {
//            assertEquals(paidCheckouts.get(i).getProducts().size(), history.getPaidCheckouts().get(i).getProducts().size());
//            assertEquals(paidCheckouts.get(i).getTotalPrice(), history.getPaidCheckouts().get(i).getTotalPrice());
//        }
//    }
}