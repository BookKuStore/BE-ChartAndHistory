package ChartAndHistory.backend.repository;

import ChartAndHistory.backend.models.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    void testFindByOwnerId() {
        Cart cart = new Cart("ownerId", null);
        cartRepository.save(cart);

        Optional<Cart> foundCart = cartRepository.findByOwnerId("ownerId");

        assertTrue(foundCart.isPresent());
        assertEquals("ownerId", foundCart.get().getOwnerId());
    }

    @Test
    void testDeleteByOwnerId() {
        Cart cart = new Cart("ownerId", null);
        cartRepository.save(cart);

        cartRepository.deleteByOwnerId("ownerId");

        Optional<Cart> foundCart = cartRepository.findByOwnerId("ownerId");
        assertFalse(foundCart.isPresent());
    }
}
