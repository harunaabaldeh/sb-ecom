package com.ecommerce.sb_ecom.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "category_name", nullable = false, unique = true)
    private String categoryName;

    public Category(Long categoryId, String categoryName) {
        this.Id = categoryId;
        this.categoryName = categoryName;
    }

    public Category() {

    }

    public Long getCategoryId() {
        return Id;
    }

    public void setCategoryId(Long categoryId) {
        this.Id = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
