package ChartAndHistory.backend.services;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.Product;
import ChartAndHistory.backend.repository.CartRepository;
import ChartAndHistory.backend.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    private static CartServiceImpl instance;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
        if (instance == null) {
            synchronized (CartServiceImpl.class) {
                if (instance == null) {
                    instance = this;
                }
            }
        }
    }

//    public static CartServiceImpl getInstance() {
//        return instance;
//    }

    @Override
    public Cart getCartByOwnerId(String ownerId) {
        return cartRepository.findByOwnerId(ownerId).orElse(new Cart(ownerId, new ArrayList<>()));
    }

    @Override
    public Cart addProductToCart(String ownerId, Product product) {
        Cart cart = getCartByOwnerId(ownerId);
        cart.getProducts().add(product);
        return cartRepository.save(cart);
    }

    @Override
    public boolean removeProductFromCart(String ownerId, long productId) {
        Optional<Cart> cartOptional = cartRepository.findByOwnerId(ownerId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            boolean removed = cart.getProducts().removeIf(product -> product.getProductId() == productId);
            if (removed) {
                cartRepository.save(cart);
            }
            return removed;
        }
        return false;
    }

    @Override
    public void clearCart(String ownerId) {
        cartRepository.deleteByOwnerId(ownerId);
    }
}
