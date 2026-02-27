package com.example.libraryManager.service;

import com.example.libraryManager.dto.CategoryDto;
import com.example.libraryManager.model.Category;
import com.example.libraryManager.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<CategoryDto> getAllCategories(){
        return categoryRepository.findAll().stream().map(
                Category::toDto
        ).collect(Collectors.toList());
    }

    public long getCategoriesCount(){
        return categoryRepository.count();
    }
    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }
}
