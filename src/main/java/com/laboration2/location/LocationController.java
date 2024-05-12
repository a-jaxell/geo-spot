package com.laboration2.location;


import com.laboration2.location.dto.LocationUpdateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable int id) {
        Location location = locationService.getLocationById(id);
        return ResponseEntity.ok(location);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Location> createLocation(@Valid @RequestBody LocationDto location) {
        Location createdLocation = locationService.createLocation(location);
        URI locationURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(createdLocation.getId())
                .toUri();
        return ResponseEntity.created(locationURI).body(createdLocation);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable int id, @RequestBody LocationUpdateDto location) {
        Location updatedLocation = locationService.updateLocation(id, location);
        URI locationURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(updatedLocation.getId())
                .toUri();
        return ResponseEntity.created(locationURI).body(updatedLocation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable int id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nearby")
    public ResponseEntity<?> locationsWithinDistance(@RequestParam Double lat, @RequestParam Double lng, @RequestParam Double radius
    ) {
        List<Location> locations = locationService.getLocationsWithinSphere(lat, lng, radius);
        return ResponseEntity.ok(locations);
    }

    @GetMapping(value = "/category/{categoryId}")
    public ResponseEntity<List<Location>> locationsInCategory(@PathVariable Long categoryId) {
        List<Location> locations = locationService.getLocationsInCategory(categoryId);
        if (locations.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(locations);
    }
}