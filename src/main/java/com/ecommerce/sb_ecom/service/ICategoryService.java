package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.payload.CategoryDto;
import com.ecommerce.sb_ecom.payload.CategoryRequest;
import com.ecommerce.sb_ecom.payload.CategoryResponse;

public interface ICategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDto createCategory(CategoryRequest categoryRequest);

    CategoryDto deleteCategory(Long categoryId);

    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    Category findCategoryById(Long Id);
}
