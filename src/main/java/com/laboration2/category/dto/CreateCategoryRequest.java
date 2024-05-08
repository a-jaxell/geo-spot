package com.laboration2.category.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.laboration2.category.Category}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCategoryRequest implements Serializable {
    @Size(max = 255)
    String name;
    @Size(max = 255)
    String symbol;
    @Size(max = 255)
    String description;
}