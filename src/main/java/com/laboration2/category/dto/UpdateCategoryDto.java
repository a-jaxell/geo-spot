package com.laboration2.category.dto;

import com.laboration2.category.Category;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link Category}
 */
public record UpdateCategoryDto(@Size(max = 255) String name, @Size(max = 255) String symbol,
                                @Size(max = 255) String description) implements Serializable {
}