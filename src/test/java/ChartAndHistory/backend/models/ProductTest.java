package ChartAndHistory.backend.models;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testProductCreation() {
        UUID id = UUID.randomUUID();
        String name = "Sample Product";
        double price = 10.0;
        String imageUrl = "http://example.com/image.jpg";
        String tokenBuku = "lala";

        Product product = new Product(id, name, price, imageUrl, tokenBuku);

        assertEquals(id, product.getProductId());
        assertEquals(name, product.getProductName());
        assertEquals(price, product.getPrice());
        assertEquals(imageUrl, product.getImageUrl());
        assertEquals(tokenBuku, product.getTokenBuku());
    }
}