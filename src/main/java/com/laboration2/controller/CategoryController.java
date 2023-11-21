package com.laboration2.controller;

import com.laboration2.entities.Category;
import com.laboration2.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService categoryService){
        this.service = categoryService;
    }
    @GetMapping
    public List<Category> getAllCategories(){
        return service.getAllCategories();
    }
    @GetMapping("{id}")
    public Category getCategoryById(@PathVariable Long id){
        return service.getOneCategory(id);
    }
}
