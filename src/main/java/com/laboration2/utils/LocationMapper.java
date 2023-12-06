package com.laboration2.utils;

import com.laboration2.location.Location;
import com.laboration2.location.LocationDto;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    private final ModelMapper modelMapper;

    public LocationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public LocationDto convertToDto(Location location) throws MappingException {
        return modelMapper.map(location, LocationDto.class);
    }

    public Location convertToEntity(LocationDto locationDto) throws MappingException {
        return modelMapper.map(locationDto, Location.class);
    }
}