package com.laboration2.location;

import com.laboration2.user.UserDto;
import com.laboration2.utils.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(int id) {
        Optional<Location> optionalLocation = locationRepository.findById(id);
        return optionalLocation.orElse(null);
    }

    public Location createLocation(Location location) {
        // You can add additional validation or business logic here before saving to the repository
        return locationRepository.save(location);
    }

    public Location updateLocation(int id, Location location) {
        if (locationRepository.existsById(id)) {
            location.setId(id); // Ensure the ID is set for the correct location
            return locationRepository.save(location);
        }
        return null; // Location with the given ID not found
    }

    public void deleteLocation(int id) {
        locationRepository.deleteById(id);
    }
}
// Viktigt att swappa longitud och latitud SQL,  har motsatt lagring
// Ha transactional på add place, för att undvika
//Har man bara en save så behövs transactional inte egentligen


