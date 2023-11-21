package com.laboration2.dto;

import com.laboration2.entities.User;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record UserDto(Long id, @Size(max = 255) String firstName,
                      @Size(max = 255) String lastName) implements Serializable {
}