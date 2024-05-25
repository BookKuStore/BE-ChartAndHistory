package ChartAndHistory.backend.services;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.Product;
import ChartAndHistory.backend.repository.CartRepository;
import ChartAndHistory.backend.services.CartService;
import ChartAndHistory.backend.observers.CartObserver;
import ChartAndHistory.backend.observers.CartSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService, CartObserver {

    private final CartRepository cartRepository;
    private final CartSubject cartSubject = new CartSubject();

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
        cartSubject.attach(this);
    }

    @Override
    public Cart saveCart(Cart cart) {
        Cart savedCart = cartRepository.save(cart);
        cartSubject.setCart(savedCart);
        return savedCart;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public void addProductToCart(long cartId, Product product) {
        Cart cart = getCartById(cartId);
        if (cart != null) {
            cart.addProduct(product);
            saveCart(cart);
        }
    }

    @Override
    public void removeProductFromCart(long cartId, Product product) {
        Cart cart = getCartById(cartId);
        if (cart != null) {
            cart.removeProduct(product);
            saveCart(cart);
        }
    }

    @Override
    public void update(Cart cart) {
        System.out.println("Cart updated: " + cart);
    }

    @Override
    public void deleteCartById(long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void resetCart(long cartId) {
        Cart cart = getCartById(cartId);
        if (cart != null) {
            cart.emptyCart();
            saveCart(cart);
        }
    }

    @Override
    public Product getProductFromCartById(long cartId, long productId) {
        Cart cart = getCartById(cartId);
        if (cart != null) {
            for (Product product : cart.getProducts()) {
                if (product.getProductId() == productId) {
                    return product;
                }
            }
        }
        return null;
    }

    @Override
    public void removeProductFromCartByProductId(long cartId, long productId) {
        Cart cart = getCartById(cartId);
        if (cart != null) {
            cart.getProducts().removeIf(product -> product.getProductId() == productId);
            saveCart(cart);
        }
    }
}