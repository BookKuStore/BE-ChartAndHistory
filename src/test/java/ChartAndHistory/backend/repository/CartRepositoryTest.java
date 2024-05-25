package ChartAndHistory.backend.repository;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.Product;
import ChartAndHistory.backend.repository.CartRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.List;

@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testSaveCart() {
        Cart cart = new Cart();
        cartRepository.save(cart);

        assertTrue(cart.getCartId() > 0);
    }

    @Test
    public void testFindAllCarts() {
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        List<Cart> carts = cartRepository.findAll();

        assertEquals(2, carts.size());
    }

    @Test
    public void testFindCartById() {
        Cart cart = new Cart();
        cartRepository.save(cart);

        Cart foundCart = cartRepository.findById(cart.getCartId()).orElse(null);

        assertEquals(cart, foundCart);
    }

    @Test
    public void testDeleteCartById() {
        Cart cart = new Cart();
        cartRepository.save(cart);

        cartRepository.deleteById(cart.getCartId());

        assertTrue(cartRepository.findById(cart.getCartId()).isEmpty());
    }
}
