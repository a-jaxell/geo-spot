package com.laboration2.category;

import com.laboration2.category.Category;
import com.laboration2.category.dto.CategoryDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends ListCrudRepository<Category, Long> {


    //ProjectionCategory findCategoryById(@Param("categoryId") Long categoryId);
    // List<ProjectionCategory> findAllProjectedBy();
}

// vill ju inte ta med visible = false för de är privata
// redan i repositylagret använda sig av DTOs kan var smart
