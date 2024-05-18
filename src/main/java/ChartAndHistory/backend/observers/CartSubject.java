package ChartAndHistory.backend.observers;

import ChartAndHistory.backend.models.Cart;
import java.util.ArrayList;
import java.util.List;

public class CartSubject {
    private final List<CartObserver> observers = new ArrayList<>();
    private Cart cart;

    public void attach(CartObserver observer) {
        observers.add(observer);
    }

    public void detach(CartObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (CartObserver observer : observers) {
            observer.update(cart);
        }
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        notifyObservers();
    }
}

