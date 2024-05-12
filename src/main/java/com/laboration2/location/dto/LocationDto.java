package com.laboration2.location.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.laboration2.location.Location;
import com.laboration2.utils.Point2DDeserializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import java.io.Serializable;

/**
 * DTO for {@link Location}
 */

public record LocationDto(@NotBlank @Size(max = 255) String locationName, Boolean visible,
                          @Size(max = 255) String description, @NotNull Long categoryId,
                          @NotNull @JsonDeserialize(using = Point2DDeserializer.class) Point<G2D> coordinates) implements Serializable {
    /**
     * Factory method to handle setting of default values.
     * If a locations visible status isnt set, it will default to true.
     */
    public static LocationDto of(@NotBlank @Size(max = 255) String locationName, Boolean visible,
                                 @Size(max = 255) String description, @NotNull Long categoryId,
                                 @NotNull @JsonDeserialize(using = Point2DDeserializer.class) Point<G2D> coordinates) {
        if (visible == null) {
            visible = true;
        }
        return new LocationDto(locationName, visible, description, categoryId, coordinates);
    }
}