package com.laboration2.location;

import com.laboration2.location.Location;
import com.laboration2.location.LocationRepository;
import com.laboration2.user.UserDto;
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

    static Optional<LocationDto> map (Optional<Location> location) {
        if(location.isEmpty()){
            return Optional.empty();
        }
        var location1 = location.get();
        return Optional.of(
                new LocationDto(
                    location1.getId(),
                    location1.getLocationName(),
                    location1.getIsPrivate(),
                    location1.getLastEdit(),
                    location1.getDateCreated(),
                    location1.getDescription(),
                    location1.getCategory().getName(),
                    new UserDto(
                        location1.getUser().getId(),
                        location1.getUser().getFirstName(),
                        location1.getUser().getLastName()
                    )
                )
        );
    }
}
