package com.ECommerceAPI.ECommerceAPI.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ECommerceAPI.ECommerceAPI.model.Product;
import com.ECommerceAPI.ECommerceAPI.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                       .map(existingProduct -> {
                            existingProduct.setName(productDetails.getName());
                            existingProduct.setDescription(productDetails.getDescription());
                            existingProduct.setPrice(productDetails.getPrice());
                            return productRepository.save(existingProduct);
                       });

    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
