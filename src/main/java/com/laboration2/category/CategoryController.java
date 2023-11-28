package com.laboration2.category;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
        var city = service.getOneCategory(id);
        return city.map( categoryDto -> ResponseEntity.ok().body(categoryDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
