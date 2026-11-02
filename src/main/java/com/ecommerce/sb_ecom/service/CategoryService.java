package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.excetions.APIException;
import com.ecommerce.sb_ecom.excetions.ResourceNotFoundException;
import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.payload.CategoryDto;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import com.ecommerce.sb_ecom.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty())
            throw new APIException("No categories found!");

        List<CategoryDto> categoryDTO = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTO);

        return categoryResponse;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category dbCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (dbCategory != null)
            throw new APIException("Category  with the name " + category.getCategoryName() + " already exists");
       Category savedCategory =  categoryRepository.save(category);
       return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepository.delete(category);
        return "Category with categoryId: " + categoryId + " deleted successfully";
    }

    @Override
    public Category updateCategory(Category categoryDto, Long categoryId) {

        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        categoryDto.setCategoryName(categoryDto.getCategoryName());
        savedCategory = categoryRepository.save(categoryDto);

        return savedCategory;
    }

    @Override
    public Category findCategoryById(Long Id) {
        return categoryRepository.findById(Id).orElseThrow();
    }
}
