package com.laboration2.category;

import com.laboration2.category.dto.CreateCategoryRequest;
import com.laboration2.category.dto.UpdateCategoryDto;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping()
    @RolesAllowed("ADMIN")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest category) {
        System.out.println("Received category: " + category.getName()); // Debug print
        try {
            Category savedCategory = categoryService.createCategory(category);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand(savedCategory.getId())
                    .toUri();

            return ResponseEntity.created(location).body(savedCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, UpdateCategoryDto category) {
        return categoryService.updateCategory(id, category);
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
