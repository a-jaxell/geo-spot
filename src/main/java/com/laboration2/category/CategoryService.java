package com.laboration2.category;
import com.laboration2.category.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    CategoryRepository repository;

    public CategoryService(CategoryRepository categoryRepository){
        this.repository = categoryRepository;
    }

    public Optional<CategoryDto> getOneCategory(Long id) {
        return map(repository.findById(id));
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    static Optional<CategoryDto> map(Optional<Category> category){
        if(category.isEmpty()){
            return Optional.empty();
        }
        var category1 = category.get();
        return Optional.of(
                new CategoryDto(category1.getId(), category1.getName(), category1.getSymbol(), category1.getDescription(),
                        category1.getLocations()
                ));
    }
}
