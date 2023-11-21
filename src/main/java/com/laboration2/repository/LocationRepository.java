package com.laboration2.repository;

import com.laboration2.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends ListCrudRepository<Location, Integer> {
    List<Location> findAllBy();
}