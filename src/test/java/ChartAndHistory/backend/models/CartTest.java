package ChartAndHistory.backend.models;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartTest {

    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    void addProductTest() {
        Product product = new Product();
        product.setPrice(10.0);
        cart.addProduct(product);
        assertEquals(10.0, cart.getTotalPrice());
    }

    @Test
    void removeProductTest() {
        Product product = new Product();
        product.setPrice(10.0);
        cart.addProduct(product);
        cart.removeProduct(product);
        assertEquals(0.0, cart.getTotalPrice());
    }

    // Test other methods similarly
}