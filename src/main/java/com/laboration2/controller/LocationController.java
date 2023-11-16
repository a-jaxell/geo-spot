package com.laboration2.controller;

import com.laboration2.entities.Location;
import com.laboration2.service.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/location")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService locationService){
        this.service = locationService;
    }

    @GetMapping
    public List<Location> getLocations(){
        return service.getAllLocations();
    }
}
