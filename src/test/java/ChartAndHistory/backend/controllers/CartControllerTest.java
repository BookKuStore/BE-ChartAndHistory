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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCart() {
        Cart cart = new Cart();
        when(cartService.saveCart(any())).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.createCart(cart);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    public void testGetAllCarts() {
        List<Cart> carts = new ArrayList<>();
        when(cartService.getAllCarts()).thenReturn(carts);

        ResponseEntity<List<Cart>> response = cartController.getAllCarts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carts, response.getBody());
    }

    @Test
    public void testGetCartById() {
        Cart cart = new Cart();
        long id = 1;
        when(cartService.getCartById(id)).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.getCartById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

//    @Test
//    public void testAddProductToCart() {
//        Cart cart = new Cart();
//        Product product = new Product();
//        long id = 1;
//        when(cartService.addProductToCart(id, product)).thenReturn(cart);
//
//        ResponseEntity<Void> response = cartController.addProductToCart(id, product);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }

    @Test
    public void testRemoveProductFromCart() {
        Cart cart = new Cart();
        Product product = new Product();
        long id = 1;
        ResponseEntity<Void> response = cartController.removeProductFromCart(id, product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cartService, times(1)).removeProductFromCart(id, product);
    }

    @Test
    public void testDeleteCartById() {
        long id = 1;
        ResponseEntity<Void> response = cartController.deleteCartById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cartService, times(1)).deleteCartById(id);
    }

    @Test
    public void testResetCart() {
        long id = 1;
        ResponseEntity<Void> response = cartController.resetCart(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cartService, times(1)).resetCart(id);
    }

    @Test
    public void testGetProductFromCartById() {
        Product product = new Product();
        long cartId = 1;
        long productId = 1;
        when(cartService.getProductFromCartById(cartId, productId)).thenReturn(product);

        ResponseEntity<Product> response = cartController.getProductFromCartById(cartId, productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testRemoveProductFromCartByProductId() {
        long id = 1;
        long productId = 1;
        ResponseEntity<Void> response = cartController.removeProductFromCartByProductId(id, productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cartService, times(1)).removeProductFromCartByProductId(id, productId);
    }
}

