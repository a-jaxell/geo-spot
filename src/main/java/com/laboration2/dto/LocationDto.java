package com.laboration2.dto;

import com.laboration2.entities.Location;
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