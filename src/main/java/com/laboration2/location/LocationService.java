package com.laboration2.location;

import com.laboration2.category.Category;
import com.laboration2.category.CategoryRepository;
import com.laboration2.exception.NotAuthenticatedException;
import com.laboration2.exception.ResourceNotFoundException;
import com.laboration2.location.dto.LocationDto;
import com.laboration2.location.dto.LocationUpdateDto;
import com.laboration2.user.User;
import com.laboration2.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository,
                           CategoryRepository categoryRepository, UserRepository userRepository) {
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Location> getAllLocations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username;
        List<Location> locations = locationRepository.findByVisibleTrue();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
            locations.addAll(locationRepository.findByVisibleFalseAndUserFirstNameEquals(username));
        }
        return locations;
    }

    public Location getLocationById(Integer id) {
        return locationRepository.findByIdAndVisibleTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no location with id '" + id + "'"));
    }

    @Transactional
    public Location createLocation(LocationDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();
            user = (User) userRepository.findByFirstName(username)
                    .orElseThrow(() -> new ResourceNotFoundException("No user found."));
        } else {
            throw new NotAuthenticatedException("No authenticated user found.");
        }
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category id '" + dto.categoryId() + "' does not exist.")
                );

        Location location = new Location();
        location.setUser(user);
        location.setCategory(category);
        location.setLocationName(dto.locationName());
        location.setDescription(dto.description());
        location.setCoordinates(dto.coordinates());
        location.setVisible(dto.visible());

        return locationRepository.save(location);
    }

    @Transactional
    public Location updateLocation(Integer id, LocationUpdateDto dto) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location does not exist."));

        Optional.ofNullable(dto.locationName()).ifPresent(location::setLocationName);
        Optional.ofNullable(dto.visible()).ifPresent(location::setVisible);
        Optional.ofNullable(dto.description()).ifPresent(location::setDescription);

        return locationRepository.save(location);
    }

    @Transactional
    public void deleteLocation(int id) throws IllegalArgumentException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();
            user = (User) userRepository.findByFirstName(username)
                    .orElseThrow(() -> new ResourceNotFoundException("Username not found."));
        } else {
            throw new NotAuthenticatedException("No authenticated user found.");
        }
        int deleted = locationRepository.deleteByIdAndUserId(id, user.getId());
        if (deleted == 0) {
            throw new ResourceNotFoundException("Unable to delete Location with id '" + id + "'");
        }
    }

    public List<Location> getLocationsInArea(String polygon) {
        return locationRepository.findWithinPolygon(polygon);
    }

    public List<Location> getLocationsWithinSphere(double lat, double lng, double radius) {
        return locationRepository.findLocationsWithinDistance(lat, lng, radius);
    }

    public List<Location> getLocationsInCategory(Long categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("Category id cannot be null");
        }
        return locationRepository.findByVisibleTrueAndCategoryIdEquals(categoryId);
    }
}