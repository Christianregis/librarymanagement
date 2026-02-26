package com.example.libraryManager.service;

import com.example.libraryManager.dto.CategoryDto;
import com.example.libraryManager.model.Category;
import com.example.libraryManager.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    public final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto saveCategory(Category category){
        return categoryRepository.save(category).toDto();
    }

    public CategoryDto updateCategory(Long id, Category newCategory){
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.setName(newCategory.getName() != null ? newCategory.getName() : category.getName());
            return categoryRepository.save(category).toDto();
        }
        return null;
    }

    public Boolean deleteCategory(Long id){
        Category category = categoryRepository.findById(id).orElse(null);
        if(category != null){
            categoryRepository.delete(category);
            return true;
        }
        return false;
    }
}
