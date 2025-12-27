package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Product;
import com.examly.springapp.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(service.addProduct(product), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return service.getProductById(id)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @RequestBody Product product) {

        return service.updateProduct(id, product)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<List<Product>> getProductByCategoryName(@PathVariable String name){
        List<Product> lp = service.getProductByCategoryName(name);
        return new ResponseEntity<>(lp, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getProductByName(@PathVariable  String name){
        List<Product> products = service.getProductByName(name);
        
        if(products.isEmpty()){
            return new ResponseEntity<>("No products found with name: "+ name, HttpStatus.NOT_FOUND);
        }
        else{
            return new  ResponseEntity<>(products, HttpStatus.OK);
        }

    }
}
