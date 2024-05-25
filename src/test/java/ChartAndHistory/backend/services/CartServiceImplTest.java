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

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.*;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.Product;
import ChartAndHistory.backend.repository.CartRepository;
import ChartAndHistory.backend.services.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveCart() {
        Cart cart = new Cart();
        cart.setTotalPrice(100.0);

        when(cartRepository.save(cart)).thenReturn(cart);

        Cart savedCart = cartService.saveCart(cart);

        assertEquals(cart.getTotalPrice(), savedCart.getTotalPrice());
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void getAllCarts() {
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart());
        carts.add(new Cart());

        when(cartRepository.findAll()).thenReturn(carts);

        List<Cart> retrievedCarts = cartService.getAllCarts();

        assertEquals(carts.size(), retrievedCarts.size());
        verify(cartRepository, times(1)).findAll();
    }

    @Test
    void getCartById() {
        long cartId = 1;
        Cart cart = new Cart();
        cart.setCartId(cartId);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        Cart retrievedCart = cartService.getCartById(cartId);

        assertEquals(cartId, retrievedCart.getCartId());
        verify(cartRepository, times(1)).findById(cartId);
    }

    @Test
    void addProductToCart() {
        long cartId = 1;
        Cart cart = new Cart();
        cart.setCartId(cartId);

        Product product = new Product();
        product.setProductId(1);
        product.setPrice(10.0);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        cartService.addProductToCart(cartId, product);

        assertEquals(1, cart.getProducts().size());
        assertEquals(product.getPrice(), cart.getTotalPrice());
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void removeProductFromCart() {
        long cartId = 1;
        Cart cart = new Cart();
        cart.setCartId(cartId);

        Product product = new Product();
        product.setProductId(1);
        product.setPrice(10.0);

        cart.addProduct(product);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        cartService.removeProductFromCart(cartId, product);

        assertEquals(0, cart.getProducts().size());
        assertEquals(0.0, cart.getTotalPrice());
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void deleteCartById() {
        long cartId = 1;

        cartService.deleteCartById(cartId);

        verify(cartRepository, times(1)).deleteById(cartId);
    }

    @Test
    void resetCart() {
        long cartId = 1;
        Cart cart = new Cart();
        cart.setCartId(cartId);

        Product product = new Product();
        product.setProductId(1);
        product.setPrice(10.0);

        cart.addProduct(product);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        cartService.resetCart(cartId);

        assertEquals(0, cart.getProducts().size());
        assertEquals(0.0, cart.getTotalPrice());
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void getProductFromCartById() {
        long cartId = 1;
        long productId = 1;

        Cart cart = new Cart();
        cart.setCartId(cartId);

        Product product = new Product();
        product.setProductId(productId);
        product.setPrice(10.0);

        cart.addProduct(product);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        Product retrievedProduct = cartService.getProductFromCartById(cartId, productId);

        assertEquals(product, retrievedProduct);
    }

//    @Test
//    void removeProductFromCartByProductId() {
//        long cartId = 1;
//        long productId = 1;
//
//        Cart cart = new Cart();
//        cart.setCartId(cartId);
//
//        Product product = new Product();
//        product.setProductId(productId);
//        product.setPrice(10.0);
//
//        cart.addProduct(product);
//
//        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
//
//        cartService.removeProductFromCartByProductId(cartId, productId);
//
//        assertEquals(0, cart.getProducts().size());
//        assertEquals(0.0, cart.getTotalPrice());
//        verify(cartRepository, times(1)).save(cart);
//    }
}