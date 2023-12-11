package com.laboration2.location;

import com.laboration2.category.Category;
import com.laboration2.utils.LocationMapper;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.geolatte.geom.C2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable int id) {
        Location location = locationService.getLocationById(id);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location createdLocation = locationService.createLocation(location);
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable int id, @RequestBody Location location) {
        Location updatedLocation = locationService.updateLocation(id, location);
        return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable int id) {
        locationService.deleteLocation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<Location>> nearbyLocations(@RequestParam double lat,
                                                          @RequestParam double lng,
                                                          @RequestParam double radius
    ){
        return ResponseEntity.ok().body(locationService.nearbyLocations(lat, lng, radius));
    }
    @PostMapping("/test")
    public ResponseEntity<List<Location>> nearby(@RequestBody String polygon){
        return ResponseEntity.ok().body(locationService.getLocationsInArea(polygon));
    }
    @GetMapping(value = "/category/{categoryId}")
    public ResponseEntity<List<Location>> locationsInCategory(@PathVariable Integer categoryId){
        List<Location> locations = locationService.getLocationsInCategory(categoryId);
            if(locations.isEmpty()){
                return ResponseEntity.notFound().build();
            }

        return ResponseEntity.ok().body(locations);
    }

}

