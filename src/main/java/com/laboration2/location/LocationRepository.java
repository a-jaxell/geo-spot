package com.laboration2.location;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends ListCrudRepository<Location, Integer> {

    @Query("SELECT l FROM Location l WHERE ST_Within(l.coordinates, ST_GeomFromText(:polygon, 4326)) = true")
    List<Location> findWithinPolygon(String polygon);

    @Query("SELECT l FROM Location l WHERE ST_Distance_Sphere(l.coordinates, ST_GeomFromText(CONCAT('POINT(', ?1, ' ', ?2, ')'), 4326)) <= ?3")
    List<Location> findLocationsWithinDistance(double lat, double lng, double rad);

    List<Location> findByVisibleTrue();

    Collection<? extends Location> findByVisibleFalseAndUserFirstNameEquals(String username);

    List<Location> findByVisibleTrueAndCategoryIdEquals(Long categoryId);

    Optional<Location> findById(Integer id);

    int deleteByIdAndUserId(int id, Long id1);

    Optional<Location> findByIdAndVisibleTrue(Integer id);
}
//Entitygraph använder vi om vi har fetchType LAZY på en @ManyToOne relation
