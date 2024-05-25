package ChartAndHistory.backend.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testProductConstructor() {
        Product product = new Product(1, "Test Product", 10.0, "test-url");

        assertEquals(1, product.getProductId());
        assertEquals("Test Product", product.getProductName());
        assertEquals(10.0, product.getPrice());
        assertEquals("test-url", product.getImageUrl());
    }
}

