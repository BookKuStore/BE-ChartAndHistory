package ChartAndHistory.backend.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void testCartGettersAndSetters() {
        Cart cart = new Cart();
        cart.setOwnerId("ownerId");

        assertEquals("ownerId", cart.getOwnerId());
    }

    @Test
    void testCartBuilder() {
        Cart cart = Cart.builder()
                .ownerId("ownerId")
                .build();

        assertEquals("ownerId", cart.getOwnerId());
    }
}
