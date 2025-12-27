package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Category;
import com.examly.springapp.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepo.findById(id).orElse(null);
    }

    @Override
    public Category updateCategory(int id, Category category) {
        Category existing = getCategoryById(id);
        if (existing != null) {
            existing.setCategoryName(category.getCategoryName());
            return categoryRepo.save(existing);
        }
        return null;
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public Page<Category> getCategorieswithPagination(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return categoryRepo.findAll(pageable);
    }
}
