package com.example.demo.loader;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader {
    private final ProductRepository productRepository;
    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @PostConstruct
    private void loadData() {
        productRepository.saveAll(List.of(
                new Product("The cheapest product", 100.1),
                new Product("Product", 200.2),
                new Product("More expensive product", 300.3),
                new Product("The most expensive product", 400.0)
        ));
    }
}
