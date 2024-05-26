import ChartAndHistory.backend.controllers.CartController;
import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.Product;
import ChartAndHistory.backend.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createCartTest() {
        Cart cart = new Cart();
        when(cartService.saveCart(cart)).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.createCart(cart);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    void getAllCartsTest() {
        List<Cart> carts = new ArrayList<>();
        when(cartService.getAllCarts()).thenReturn(carts);

        ResponseEntity<List<Cart>> response = cartController.getAllCarts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carts, response.getBody());
    }

    @Test
    void getCartByIdTest() {
        long id = 1;
        Cart cart = new Cart();
        when(cartService.getCartById(id)).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.getCartById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    void addProductToCartTest() {
        long id = 1;
        Product product = new Product();

        ResponseEntity<Void> response = cartController.addProductToCart(id, product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cartService, times(1)).addProductToCart(id, product);
    }

    @Test
    void removeProductFromCartTest() {
        long id = 1;
        Product product = new Product();

        ResponseEntity<Void> response = cartController.removeProductFromCart(id, product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cartService, times(1)).removeProductFromCart(id, product);
    }

    @Test
    void deleteCartByIdTest() {
        long id = 1;

        ResponseEntity<Void> response = cartController.deleteCartById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cartService, times(1)).deleteCartById(id);
    }

    @Test
    void resetCartTest() {
        long id = 1;

        ResponseEntity<Void> response = cartController.resetCart(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cartService, times(1)).resetCart(id);
    }

    @Test
    void getProductFromCartByIdTest() {
        long cartId = 1;
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        when(cartService.getProductFromCartById(cartId, productId)).thenReturn(product);

        ResponseEntity<Product> response = cartController.getProductFromCartById(cartId, productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void removeProductFromCartByProductIdTest() {
        long id = 1;
        UUID productId = UUID.randomUUID();

        ResponseEntity<Void> response = cartController.removeProductFromCartByProductId(id, productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cartService, times(1)).removeProductFromCartByProductId(id, productId);
    }
}