package ChartAndHistory.backend.services;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.Product;

public interface CartService {
    Cart getCartByOwnerId(String ownerId);
    Cart addProductToCart(String ownerId, Product product);
    boolean removeProductFromCart(String ownerId, long productId);
    void clearCart(String ownerId);
}
