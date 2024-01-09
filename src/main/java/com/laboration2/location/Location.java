package com.laboration2.location;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.laboration2.category.Category;
import com.laboration2.user.User;
import com.laboration2.utils.Point2DSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.geolatte.geom.C2D;
import org.geolatte.geom.Point;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", nullable = false)
    private int id;

    @Size(max = 255)
    @Column(name = "location_name")
    private String locationName;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "last_edit")
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDateTime;

    @Column(name = "date_created")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDateTime;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    @JsonBackReference(value = "category-locations")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonBackReference(value = "user-locations")
    private User user;

    @JsonSerialize(using = Point2DSerializer.class)
    private Point<C2D> coordinates;
}