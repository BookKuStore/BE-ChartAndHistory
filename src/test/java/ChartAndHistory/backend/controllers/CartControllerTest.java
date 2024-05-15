package ChartAndHistory.backend.controllers;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.Product;
import ChartAndHistory.backend.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CartControllerTest {

    private CartService cartService;
    private CartController cartController;

    @BeforeEach
    void setUp() {
        cartService = Mockito.mock(CartService.class);
        cartController = new CartController(cartService);
    }

    @Test
    void getCart_whenCartExists_shouldReturnCart() {
        Cart cart = new Cart("ownerId", null);
        when(cartService.getCartByOwnerId(anyString())).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.getCart("ownerId");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    void getCart_whenCartDoesNotExist_shouldReturnNotFound() {
        when(cartService.getCartByOwnerId(anyString())).thenReturn(null);

        ResponseEntity<Cart> response = cartController.getCart("ownerId");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void addProductToCart_shouldReturnUpdatedCart() {
        Cart cart = new Cart("ownerId", null);
        Product product = new Product();
        when(cartService.addProductToCart(anyString(), any(Product.class))).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.addProductToCart("ownerId", product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    void removeProductFromCart_whenProductExists_shouldReturnOk() {
        when(cartService.removeProductFromCart(anyString(), anyLong())).thenReturn(true);

        ResponseEntity<Cart> response = cartController.removeProductFromCart("ownerId", new Product());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void removeProductFromCart_whenProductDoesNotExist_shouldReturnNotFound() {
        when(cartService.removeProductFromCart(anyString(), anyLong())).thenReturn(false);

        ResponseEntity<Cart> response = cartController.removeProductFromCart("ownerId", new Product());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void clearCart_shouldReturnNoContent() {
        ResponseEntity<Void> response = cartController.clearCart("ownerId");

        verify(cartService, times(1)).clearCart("ownerId");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
