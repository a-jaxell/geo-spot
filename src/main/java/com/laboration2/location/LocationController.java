package com.laboration2.location;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/locations")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService locationService){
        this.service = locationService;
    }

    @GetMapping
    public List<Location> getAllLocations(){
        return service.getAllLocations();
    }

    @GetMapping("{id}")
    public Optional<Location> getLocation(@PathVariable int id){
        return service.getLocationById(id);
    }

    @PostMapping
    public String createNewLocation(){
        // Should accept JSON with params needed for new location
        // requires login
        return "Create new location";
    }
    @PatchMapping
    public String modifyExistingLocation(){
        // Modify anything but coordinates on your own locations
        //Requires login
        return "Patch a location";
    }
    @DeleteMapping
    public String deleteLocation(){
        //Delete one of your own locations
        // requires login
        return "Delete an Location";
    }
}
