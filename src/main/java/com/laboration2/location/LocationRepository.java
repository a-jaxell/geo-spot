package com.laboration2.location;

import com.laboration2.location.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends ListCrudRepository<Location, Integer> {
    List<Location> findByCategory_Id(Integer category_id);

    @Query("SELECT l FROM Location l WHERE ST_Within(l.coordinates, ST_GeomFromText(:polygon, 4326)) = true")
    List<Location> findWithinPolygon(String polygon);
}
// List<Place> findPlacesByUserId(string userId);
//Entitygraph använder vi om vi har fetchType LAZY på en @ManyToOne relation
