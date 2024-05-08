package com.laboration2.category;
import com.laboration2.category.dto.CreateCategoryRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Transactional
    public Category createCategory(CreateCategoryRequest dto) {
        if(categoryRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Category with name '" +dto.getName()+"' already exists.");
        }
        Category category = new Category();

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setSymbol(dto.getSymbol());

        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category) {
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            return categoryRepository.save(category);
        }
        return null; // Handle not found scenario
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}