package ChartAndHistory.backend.models;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private List<Product> products = new ArrayList<>();

    private double totalPrice;

    public void addProduct(Product product) {
        products.add(product);
        calculateTotalPrice();
    }

    public void removeProduct(Product product) {
        products.remove(product);
        calculateTotalPrice();
    }

    public void calculateTotalPrice() {
        totalPrice = products.stream().mapToDouble(Product::getPrice).sum();
    }

    public void emptyCart() {
        products.clear();
        totalPrice = 0.0;
    }
}

