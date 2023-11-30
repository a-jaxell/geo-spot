package com.laboration2.location;

import com.laboration2.location.Location;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends ListCrudRepository<Location, Integer> {

}
// List<Place> findPlacesByUserId(string userId);
//Entitygraph använder vi om vi har fetchType LAZY på en @ManyToOne relation
