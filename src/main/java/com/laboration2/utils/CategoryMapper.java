package com.laboration2.utils;

import com.laboration2.category.Category;
import com.laboration2.category.dto.CategoryDto;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    private final ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    public CategoryDto convertToDto(Category category) throws MappingException {return modelMapper.map(category, CategoryDto.class);}

    public Category convertToEntity(CategoryDto categoryDto) throws MappingException {return modelMapper.map(categoryDto, Category.class);}
}
