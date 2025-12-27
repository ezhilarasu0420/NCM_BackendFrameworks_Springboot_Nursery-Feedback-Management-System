package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Product;
import com.examly.springapp.repository.ProductRepo;


@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public Product addProduct(Product product) {
        return repo.save(product);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return repo.findById(id);
    }

    public Optional<Product> updateProduct(int id, Product product) {
        return repo.findById(id).map(existing -> {
            existing.setProductName(product.getProductName());
            existing.setPrice(product.getPrice());
            existing.setCategory(product.getCategory());
            return repo.save(existing);
        });
    }

    public List<Product> getProductByCategoryName(String name){
        return repo.findProductByCategoryName(name);
    }

    public List<Product> getProductByName(String name){
        return repo.findByProductName(name);
    }
}
