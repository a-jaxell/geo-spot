package com.laboration2.location;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.laboration2.category.Category;
import com.laboration2.user.User;
import com.laboration2.utils.Point2DSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.geolatte.geom.C2D;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;

// Add getter/setter annotation from lombok to minimaze file size
//@Setter(AccessLevel.NONE) on field to restrict access to generated setters.
// @AllArgsConstructor
// @NoArgsConstructor

@Entity
@Getter
@Setter
@Table(name = "location")
public class Location {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", nullable = false)
    private Long id;

    @Getter
    @Size(max = 255)
    @Column(name = "location_name")
    private String locationName;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "last_edit")
    @UpdateTimestamp
    private LocalDateTime lastModifiedDateTime;

    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    @JsonBackReference
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonBackReference
    private User user;

    //Importera dependencies, geolatte, hibernate spatial.
    @JsonSerialize(using = Point2DSerializer.class) // egen definierad klass för serialisering
    private Point<C2D> coordinates;

    // Implementera equals/hashcode. JPA utilities equals/hashcode generator
    // Find a better way of representing coordinates here. Maybe there is an interface in the hibernate-spatial dependency.
}
