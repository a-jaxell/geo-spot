package com.laboration2.location;

import com.laboration2.location.Location;
import com.laboration2.location.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
