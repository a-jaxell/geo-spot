package com.laboration2.service;

import com.laboration2.entities.Location;
import com.laboration2.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    LocationRepository repository;

    public LocationService(LocationRepository locationRepository){
        this.repository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return repository.findAllLocations();
    }
}
