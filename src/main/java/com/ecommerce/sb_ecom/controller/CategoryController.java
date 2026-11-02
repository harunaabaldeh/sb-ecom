package com.ecommerce.sb_ecom.controller;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.payload.CategoryDto;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import com.ecommerce.sb_ecom.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CategoryController {
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService iCategoryService) {
        this.categoryService = iCategoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories() {
        CategoryResponse categoryResponse = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategoryDto = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(savedCategoryDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        String status = categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @PutMapping("/public/categories/{categoryId}")
    public Category updateCategory(@Valid @RequestBody Category category, @PathVariable Long categoryId) {
        return categoryService.updateCategory(category, categoryId);
    }

    @GetMapping("/public/categories/{categoryId}")
    public Category getCategoryById(@PathVariable Long categoryId){
       return categoryService.findCategoryById(categoryId);
    }
}
