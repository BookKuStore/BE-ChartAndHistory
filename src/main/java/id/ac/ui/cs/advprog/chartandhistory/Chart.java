package id.ac.ui.cs.advprog.chartandhistory;

import id.ac.ui.cs.advprog.chartandhistory.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Chart {
    String ownerId;
    List<Product> products;

    public void Order(String ownerId, List<Product> products){

    }
}