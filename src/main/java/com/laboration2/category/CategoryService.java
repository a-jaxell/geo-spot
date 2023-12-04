package com.laboration2.category;
import com.laboration2.category.dto.CategoryDto;
import com.laboration2.utils.CategoryMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryMapper categoryMapper){
        this.repository = categoryRepository;
        this.mapper = categoryMapper;
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

    public CategoryDto createNewCategory (CategoryDto categoryDto) {

        Category newCategory = mapper.convertToEntity(categoryDto);
        repository.save(newCategory);

        return mapper.convertToDto(newCategory);
    }
}
