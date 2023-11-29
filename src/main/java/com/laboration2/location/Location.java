package com.laboration2.location;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.laboration2.category.Category;
import com.laboration2.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;


import java.time.Instant;


@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "location_name")
    private String locationName;

    @Column(name = "is_private")
    private Boolean isPrivate;

    @Column(name = "last_edit")
    private Instant lastEdit;

    @Column(name = "date_created")
    private Instant dateCreated;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(Instant lastEdit) {
        this.lastEdit = lastEdit;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Find a better way of representing coordinates here. Maybe there is an interface in the hibernate-spatial dependency.
/*
    TODO [JPA Buddy] create field to map the 'coordinates' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "coordinates", columnDefinition = "geometry(0, 0)")
    private java.lang.Object coordinates;
*/
}
