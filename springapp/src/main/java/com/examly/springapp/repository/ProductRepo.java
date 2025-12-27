package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Product;

@Repository

public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Query("select p from Product as p where p.category.categoryName = :name")
    List<Product> findProductByCategoryName(@Param("name") String name);

    List<Product> findByProductName(String productName);
}
