package com.laboration2.category.dto;

import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.laboration2.category.Category}
 */
public record CreateCategoryRequest( @Size(max = 255) String name, @Size(max = 255) String symbol,
                                    @Size(max = 255) String description) implements Serializable {
}