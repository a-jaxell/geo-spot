package com.laboration2.category;

import com.laboration2.location.Location;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Category}
 */
public record CategoryDto(Long id, @Size(max = 255) String name, @Size(max = 255) String symbol,
                          @Size(max = 255) String description, Set<Location> locations) implements Serializable {
}