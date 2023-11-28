package com.laboration2.location;

import com.laboration2.category.CategoryDto;
import com.laboration2.user.UserDto;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Location}
 */
public record LocationDto(Long id, @Size(max = 255) String locationName, Boolean isPrivate, Instant lastEdit,
                          Instant dateCreated, @Size(max = 255) String description, CategoryDto category,
                          UserDto user) implements Serializable {
}