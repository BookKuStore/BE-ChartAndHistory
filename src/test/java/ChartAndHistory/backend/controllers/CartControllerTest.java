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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    private Cart cart;
    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cart = new Cart();
        cart.setCartId(1L);
        product = new Product();
        product.setProductId(1L);
        product.setProductName("Product 1");
        product.setPrice(100.0);
    }

    @Test
    void testCreateCart() {
        when(cartService.saveCart(cart)).thenReturn(cart);
        ResponseEntity<Cart> response = cartController.createCart(cart);
        assertEquals(cart, response.getBody());
        verify(cartService, times(1)).saveCart(cart);
    }

    @Test
    void testGetAllCarts() {
        List<Cart> carts = Arrays.asList(new Cart(), new Cart());
        when(cartService.getAllCarts()).thenReturn(carts);
        ResponseEntity<List<Cart>> response = cartController.getAllCarts();
        assertEquals(carts, response.getBody());
        verify(cartService, times(1)).getAllCarts();
    }

    @Test
    void testGetCartById() {
        when(cartService.getCartById(1L)).thenReturn(cart);
        ResponseEntity<Cart> response = cartController.getCartById(1L);
        assertEquals(cart, response.getBody());
        verify(cartService, times(1)).getCartById(1L);
    }

    @Test
    void testAddProductToCart() {
        doNothing().when(cartService).addProductToCart(1L, product);
        ResponseEntity<Void> response = cartController.addProductToCart(1L, product);
        assertEquals(200, response.getStatusCodeValue());
        verify(cartService, times(1)).addProductToCart(1L, product);
    }

    @Test
    void testRemoveProductFromCart() {
        doNothing().when(cartService).removeProductFromCart(1L, product);
        ResponseEntity<Void> response = cartController.removeProductFromCart(1L, product);
        assertEquals(200, response.getStatusCodeValue());
        verify(cartService, times(1)).removeProductFromCart(1L, product);
    }
}
