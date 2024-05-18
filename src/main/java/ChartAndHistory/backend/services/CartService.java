package ChartAndHistory.backend.services;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.Product;
import java.util.List;

public interface CartService {
    Cart saveCart(Cart cart);
    List<Cart> getAllCarts();
    Cart getCartById(long id);
    void addProductToCart(long cartId, Product product);
    void removeProductFromCart(long cartId, Product product);
}

