package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.payload.CategoryDto;
import com.ecommerce.sb_ecom.payload.CategoryResponse;


public interface ICategoryService {
    CategoryResponse getAllCategories();

    CategoryDto createCategory(CategoryDto categoryDto);

    String deleteCategory(Long categoryId);

    Category updateCategory(Category category, Long categoryId);

    Category findCategoryById(Long Id);
}
