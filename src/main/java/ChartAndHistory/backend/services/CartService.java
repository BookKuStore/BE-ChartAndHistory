package ChartAndHistory.backend.services;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.Product;
import java.util.List;
import java.util.UUID;

public interface CartService {
    Cart saveCart(Cart cart);
    List<Cart> getAllCarts();
    Cart getCartById(long id);
    void addProductToCart(long cartId, Product product);
    void removeProductFromCart(long cartId, Product product);
    void deleteCartById(long id);
    void resetCart(long cartId);
    Product getProductFromCartById(long cartId, UUID productId);
    void removeProductFromCartByProductId(long cartId, UUID productId);
}
