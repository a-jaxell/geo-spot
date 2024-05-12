package com.laboration2.category;

import com.laboration2.category.dto.CreateCategoryRequest;
import com.laboration2.exception.ResourceAlreadyExistsException;
import com.laboration2.exception.ResourceNotFoundException;
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
        return categoryRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Category with id '" + id + "' does not exist."));
    }

    @Transactional
    public Category createCategory(CreateCategoryRequest dto) {
        if (categoryRepository.existsByName(dto.getName())) {
            throw new ResourceAlreadyExistsException("Category with name '" + dto.getName() + "' already exists.");
        }
        Category category = new Category();

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setSymbol(dto.getSymbol());

        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}