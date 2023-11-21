package com.laboration2.repository;

import com.laboration2.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends ListCrudRepository<Category, Long> {
    Category findCategoryById(Long categoryId);
}