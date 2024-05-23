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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

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
    void testSaveCart() {
        when(cartRepository.save(cart)).thenReturn(cart);
        Cart savedCart = cartService.saveCart(cart);
        assertEquals(cart, savedCart);
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void testGetAllCarts() {
        List<Cart> carts = Arrays.asList(new Cart(), new Cart());
        when(cartRepository.findAll()).thenReturn(carts);
        List<Cart> result = cartService.getAllCarts();
        assertEquals(carts, result);
        verify(cartRepository, times(1)).findAll();
    }

    @Test
    void testGetCartById() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        Cart result = cartService.getCartById(1L);
        assertEquals(cart, result);
        verify(cartRepository, times(1)).findById(1L);
    }

//    @Test
//    void testAddProductToCart() {
//        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
//        cartService.addProductToCart(1L, product);
//        assertEquals(1, cart.getProducts().size());
//        assertEquals(product, cart.getProducts().get(0));
//        verify(cartRepository, times(2)).save(cart);
//    }
//
//    @Test
//    void testRemoveProductFromCart() {
//        cart.addProduct(product);
//        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
//        cartService.removeProductFromCart(1L, product);
//        assertEquals(0, cart.getProducts().size());
//        verify(cartRepository, times(2)).save(cart);
//    }
}
