package com.laboration2.location;

import com.laboration2.location.Location;
import com.laboration2.location.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    LocationRepository repository;

    public LocationService(LocationRepository locationRepository){
        this.repository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return repository.findAll();
    }

    public Optional<Location> getLocationById(int id) {
        return repository.findById(id);
    }
}
