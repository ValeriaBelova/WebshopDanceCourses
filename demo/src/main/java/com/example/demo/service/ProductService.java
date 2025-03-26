package com.example.demo.service;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setDuration(productDTO.getDuration());
        product.setVariations(productDTO.getVariations());
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setDuration(productDTO.getDuration());
        product.setVariations(productDTO.getVariations());
        return productRepository.save(product);
    }

    // Hämta alla produkter
    public List<Product> getAllProducts() {
        return productRepository.findAll();  // Detta returnerar alla produkter i databasen
    }


}
