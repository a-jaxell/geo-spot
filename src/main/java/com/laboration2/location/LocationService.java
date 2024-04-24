package com.laboration2.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Location> getLocationsInArea(String polygon){
        return locationRepository.findWithinPolygon(polygon);
    }

    public List<Location> nearbyLocations(double lat, double lng, double radius) {

        return locationRepository.findAll().stream()
                .filter(location ->
                        isLocationWithinRadius(location, lat, lng, radius))
                                .collect(Collectors.toList());

    }
    private boolean isLocationWithinRadius(Location location, double targetLat, double targetLng, double radius){

        double earthRadius = 6371; // Earth's radius in kilometers

        double locationLat = location.getCoordinates().getPosition().getLat();
        double locationLng = location.getCoordinates().getPosition().getLon();

        double dLat = Math.toRadians(locationLat - targetLat);
        double dLng = Math.toRadians(locationLng - targetLng);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(targetLat)) * Math.cos(Math.toRadians(locationLat)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = earthRadius * c;

        return distance <= radius;
    }
    public List<Location> getLocationsInCategory(Integer categoryId) {
        if(categoryId == null){
            throw new IllegalArgumentException("Category id cannot be null");
        }

        return locationRepository.findByCategory_Id(categoryId);

    }
}