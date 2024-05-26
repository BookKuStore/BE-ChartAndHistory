package ChartAndHistory.backend.services;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.Product;
import ChartAndHistory.backend.repository.CartRepository;
import ChartAndHistory.backend.services.CartService;
import ChartAndHistory.backend.services.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

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
    void saveCartTest() {
        Cart cart = new Cart();
        when(cartRepository.save(cart)).thenReturn(cart);
        cartService.saveCart(cart);
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void getAllCartsTest() {
        List<Cart> carts = new ArrayList<>();
        when(cartRepository.findAll()).thenReturn(carts);
        cartService.getAllCarts();
        verify(cartRepository, times(1)).findAll();
    }

    // Test other methods similarly
}