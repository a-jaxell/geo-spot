package com.laboration2.location.dto;

import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.laboration2.location.Location}
 */
public record LocationUpdateDto(@Size(max = 255) String locationName, Boolean visible,
                                @Size(max = 255) String description) implements Serializable {
}