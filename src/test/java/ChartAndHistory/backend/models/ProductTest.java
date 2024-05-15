package ChartAndHistory.backend.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testProductGettersAndSetters() {
        Product product = new Product();
        product.setProductId(1L);
        product.setProductName("Test Product");
        product.setPrice(100.0);

        assertEquals(1L, product.getProductId());
        assertEquals("Test Product", product.getProductName());
        assertEquals(100.0, product.getPrice());
    }

    @Test
    void testProductConstructor() {
        Product product = new Product(1L, "Test Product", 100.0);

        assertEquals(1L, product.getProductId());
        assertEquals("Test Product", product.getProductName());
        assertEquals(100.0, product.getPrice());
    }
}
