package ChartAndHistory.backend.repository;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.Product;
import ChartAndHistory.backend.repository.CartRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataJpaTest
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    private Cart cart;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setProductName("Product 1");
        product1.setPrice(100.0);

        product2 = new Product();
        product2.setProductName("Product 2");
        product2.setPrice(200.0);

        cart = new Cart();
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.calculateTotalPrice();
    }

    @Test
    void testSaveCart() {
        Cart savedCart = cartRepository.save(cart);
        assertEquals(cart.getCartId(), savedCart.getCartId());
        assertEquals(2, savedCart.getProducts().size());
        assertEquals(300.0, savedCart.getTotalPrice());
    }

    @Test
    void testFindAllCarts() {
        Cart savedCart = cartRepository.save(cart);
        List<Cart> carts = cartRepository.findAll();
        assertTrue(carts.contains(savedCart));
    }

    @Test
    void testFindCartById() {
        Cart savedCart = cartRepository.save(cart);
        Cart foundCart = cartRepository.findById(savedCart.getCartId()).orElse(null);
        assertEquals(savedCart, foundCart);
    }

    @Test
    void testDeleteCart() {
        Cart savedCart = cartRepository.save(cart);
        cartRepository.delete(savedCart);
        assertTrue(cartRepository.findById(savedCart.getCartId()).isEmpty());
    }
}
