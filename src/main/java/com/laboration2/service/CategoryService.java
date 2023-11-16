package com.laboration2.service;

import com.laboration2.entities.Category;
import com.laboration2.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    CategoryRepository repository;

    public CategoryService(CategoryRepository categoryRepository){
        this.repository = categoryRepository;
    }

    public Category getOneCategory(int id) {
        return repository.findBy(id);
    }
}
