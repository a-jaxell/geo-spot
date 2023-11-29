package com.laboration2.category;

import com.laboration2.category.dto.CategoryDto;
import com.laboration2.category.dto.CreateCategoryRequest;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        var category = service.getOneCategory(id);
        return category.map( categoryDto -> ResponseEntity.ok().body(categoryDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<CreateCategoryRequest> createNewCategory(@RequestBody CreateCategoryRequest request ){
        // Post shit into database, if successful. Return response with created object.
        // var category = service.getOneCategory(1L);
        //return category.map(categoryDto -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(categoryDto)).orElseGet(() -> ResponseEntity.notFound().build());
        return ResponseEntity.ok().body(request);
    }

}
