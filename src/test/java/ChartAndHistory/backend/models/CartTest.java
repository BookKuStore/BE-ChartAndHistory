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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    public void testAddProduct() {
        Cart cart = new Cart();
        Product product = new Product();
        product.setPrice(10.0);

        cart.addProduct(product);

        assertEquals(1, cart.getProducts().size());
        assertEquals(10.0, cart.getTotalPrice());
    }

    @Test
    public void testRemoveProduct() {
        Cart cart = new Cart();
        Product product = new Product();
        product.setPrice(10.0);
        cart.addProduct(product);

        cart.removeProduct(product);

        assertEquals(0, cart.getProducts().size());
        assertEquals(0.0, cart.getTotalPrice());
    }

    @Test
    public void testCalculateTotalPrice() {
        Cart cart = new Cart();
        Product product1 = new Product();
        product1.setPrice(10.0);
        Product product2 = new Product();
        product2.setPrice(20.0);
        cart.addProduct(product1);
        cart.addProduct(product2);

        cart.calculateTotalPrice();

        assertEquals(30.0, cart.getTotalPrice());
    }

    @Test
    public void testEmptyCart() {
        Cart cart = new Cart();
        Product product = new Product();
        product.setPrice(10.0);
        cart.addProduct(product);

        cart.emptyCart();

        assertTrue(cart.getProducts().isEmpty());
        assertEquals(0.0, cart.getTotalPrice());
    }
}

