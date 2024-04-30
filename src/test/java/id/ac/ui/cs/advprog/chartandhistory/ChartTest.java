package id.ac.ui.cs.advprog.chartandhistory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChartTest {
    private List<Product> products;

    @BeforeEach
    void setKeranjangBelanja(){
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId(1);
        product1.setProductName("Harry Potter");


        Product product2 = new Product();
        product2.setProductId(2);
        product2.setProductName("Tiga Manula");


        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreateChartEmptyProduct(){
        this.products.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            Chart chart = new Chart("ilhamzik", this.products);
        });
    }

    @Test
    void testChartIsSuccess(){
        Chart chart = new Chart("ilhamzik", this.products);

        assertSame(this.products, chart.getProducts());
        assertEquals(2, chart.getProducts().size());

        assertEquals(1 , chart.getProducts().get(0).getProductId());
        assertEquals("Harry Potter", chart.getProducts().get(0).getProductName());

        assertEquals(2 , chart.getProducts().get(1).getProductId());
        assertEquals("Tiga Manula", chart.getProducts().get(1).getProductName());
    }
}
