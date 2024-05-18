package ChartAndHistory.backend.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest {

    private Cart cart;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        product1 = new Product();
        product1.setProductId(1L);
        product1.setProductName("Product 1");
        product1.setPrice(100.0);

        product2 = new Product();
        product2.setProductId(2L);
        product2.setProductName("Product 2");
        product2.setPrice(200.0);
    }

    @Test
    void testAddProductToCart() {
        cart.addProduct(product1);
        assertEquals(1, cart.getProducts().size());
        assertTrue(cart.getProducts().contains(product1));
    }

    @Test
    void testRemoveProductFromCart() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.removeProduct(product1);
        assertEquals(1, cart.getProducts().size());
        assertTrue(cart.getProducts().contains(product2));
    }

    @Test
    void testCalculateTotalPrice() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.calculateTotalPrice();
        assertEquals(300.0, cart.getTotalPrice());
    }

    @Test
    void testEmptyCart() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.emptyCart();
        assertEquals(0, cart.getProducts().size());
        assertEquals(0.0, cart.getTotalPrice());
    }
}
