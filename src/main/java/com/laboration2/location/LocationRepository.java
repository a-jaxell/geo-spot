package com.laboration2.location;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface LocationRepository extends ListCrudRepository<Location, Integer> {

    @Query("SELECT l FROM Location l WHERE ST_Within(l.coordinates, ST_GeomFromText(:polygon, 4326)) = true")
    List<Location> findWithinPolygon(String polygon);

    List<Location> findByVisibleTrue();

    Collection<? extends Location> findByVisibleFalseAndUserFirstNameEquals(String username);

    List<Location> findByVisibleTrueAndCategoryIdEquals(Long categoryId);
}
//Entitygraph använder vi om vi har fetchType LAZY på en @ManyToOne relation
