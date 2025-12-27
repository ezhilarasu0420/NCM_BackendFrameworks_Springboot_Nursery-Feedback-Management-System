package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;
    private int price;

    @ManyToOne
    private Category category;

    public int getId() { 
		return id;
	 }
    public void setId(int id) {
	 	this.id = id; 
	}

    public String getProductName() { 
		return productName;
	 }
    public void setProductName(String productName) { 
		this.productName = productName;
	 }

    public int getPrice() { 
		return price; 
	}
    public void setPrice(int price) { 
		this.price = price;
	 }

    public Category getCategory() { 	
		return category;
	 }
    public void setCategory(Category category) { 
		this.category = category;
	 }
}
