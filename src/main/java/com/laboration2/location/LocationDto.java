package com.laboration2.location;

import com.laboration2.user.UserDto;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * DTO for {@link Location}
 */
public record LocationDto(Long id, @Size(max = 255) String locationName, Boolean visible, LocalDateTime lastEdit,
                          LocalDateTime dateCreated, @Size(max = 255) String description, String categoryName,
                          UserDto user) implements Serializable {
}