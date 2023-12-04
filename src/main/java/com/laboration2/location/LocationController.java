package com.laboration2.location;

import com.laboration2.utils.LocationMapper;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
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

    @GetMapping(value = "/emoji", produces = "application/json")
    public Boolean emojiTest(){
        String str = EmojiParser.parseToUnicode(":grinning:");
        return EmojiManager.isEmoji(str);
    }

    @GetMapping("{id}")
    public Location getLocation(@PathVariable int id){
        Optional<Location> location = service.getLocationById(id);

        return location.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found."));
    }

    @PostMapping
    public ResponseEntity<LocationDto> createNewLocation(LocationDto location){
        // requires login
       var locationResponse = service.createNewLocation(location);

       if(locationResponse != null){
           return ResponseEntity.created(URI.create("/locations/" + locationResponse.id())).build();
       } else {
           return ResponseEntity.badRequest().build();
       }
    }
    @PatchMapping
    public String modifyExistingLocation(){
        // Modify anything but coordinates on your own locations
        //Requires login
        return "Patch a location";
    }
    @DeleteMapping
    public String deleteLocation() {
        //Delete one of your own locations
        // requires login
        return "Delete an Location";
    }
}

