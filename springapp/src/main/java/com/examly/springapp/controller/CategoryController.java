package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Category;
import com.examly.springapp.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody(required = false) Category category) {

        if (category == null ||
            category.getCategoryName() == null ||
            category.getCategoryName().trim().isEmpty()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Category saved = categoryService.createCategory(category);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> list = categoryService.getAllCategories();

        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);   
        }

        return new ResponseEntity<>(list, HttpStatus.OK);        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        return new ResponseEntity<>(
                categoryService.getCategoryById(id),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable int id,
            @RequestBody Category category) {

        return new ResponseEntity<>(
                categoryService.updateCategory(id, category),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/page/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<Category>> getCategorieswithPagination(@PathVariable int pageNumber, @PathVariable int pageSize){
        Page<Category> l = categoryService.getCategorieswithPagination(pageNumber, pageSize);
        return new ResponseEntity<>(l,HttpStatus.OK);
    }
}
