package com.laboration2.repository;

import com.laboration2.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findAllLocations();
}