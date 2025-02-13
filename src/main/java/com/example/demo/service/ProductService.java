package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public ResponseEntity<Product> updateOrCreate(Product product, Long id) {
        Optional<Product> existingProductOpt = this.findById(id);

        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            this.save(existingProduct);

            return new ResponseEntity<>(existingProduct, HttpStatus.OK);
        } else {
            this.save(product);

            return new ResponseEntity<>(product, HttpStatus.CREATED);
        }
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}