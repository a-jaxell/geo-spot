package com.laboration2.location;

import com.laboration2.user.UserDto;
import com.laboration2.utils.LocationMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    LocationRepository repository;
    LocationMapper mapper;

    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper){
        this.repository = locationRepository; this.mapper = locationMapper;
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
                    location1.getVisible(),
                    location1.getLastModifiedDateTime(),
                    location1.getCreatedDateTime(),
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

    public LocationDto createNewLocation(LocationDto locationDto){

        Location newLocation = mapper.convertToEntity(locationDto);
        // repository.save(newLocation);

        return mapper.convertToDto(newLocation);
    }
}

// Viktigt att swappa longitud och latitud SQL,  har motsatt lagring
// Ha transactional på add place, för att undvika
//Har man bara en save så behövs transactional inte egentligen


