package ChartAndHistory.backend.services;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.Product;
import ChartAndHistory.backend.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    private CartRepository cartRepository;
    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        cartRepository = Mockito.mock(CartRepository.class);
        cartService = new CartServiceImpl(cartRepository);
    }

    @Test
    void getCartByOwnerId_shouldReturnCart() {
        Cart cart = new Cart("ownerId", new ArrayList<>());
        when(cartRepository.findByOwnerId(anyString())).thenReturn(Optional.of(cart));

        Cart result = cartService.getCartByOwnerId("ownerId");

        assertEquals("ownerId", result.getOwnerId());
    }

    @Test
    void addProductToCart_shouldAddProduct() {
        Cart cart = new Cart("ownerId", new ArrayList<>());
        Product product = new Product(1L, "Product", 10.0);
        when(cartRepository.findByOwnerId(anyString())).thenReturn(Optional.of(cart));
        when(cartRepository.save(cart)).thenReturn(cart);

        Cart result = cartService.addProductToCart("ownerId", product);

        assertEquals(1, result.getProducts().size());
        assertEquals(product, result.getProducts().get(0));
    }

    @Test
    void removeProductFromCart_shouldRemoveProduct() {
        Product product = new Product(1L, "Product", 10.0);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        Cart cart = new Cart("ownerId", products);
        when(cartRepository.findByOwnerId(anyString())).thenReturn(Optional.of(cart));

        boolean result = cartService.removeProductFromCart("ownerId", 1L);

        assertTrue(result);
        assertTrue(cart.getProducts().isEmpty());
    }

    @Test
    void removeProductFromCart_shouldReturnFalseWhenProductNotFound() {
        Cart cart = new Cart("ownerId", new ArrayList<>());
        when(cartRepository.findByOwnerId(anyString())).thenReturn(Optional.of(cart));

        boolean result = cartService.removeProductFromCart("ownerId", 1L);

        assertFalse(result);
    }

    @Test
    void clearCart_shouldClearCart() {
        cartService.clearCart("ownerId");

        verify(cartRepository, times(1)).deleteByOwnerId("ownerId");
    }
}
