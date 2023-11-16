package com.laboration2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;


import java.time.LocalDate;


@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer location_id;
    @Size(max=255)
    private String locationName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @NotNull
    private Long user_id;
    @NotNull
    private boolean isPrivate;
    @FutureOrPresent
    private LocalDate lastEdit;
    @PastOrPresent
    private LocalDate dateCreated;
    @Size(max = 255)
    private String description;
    @NotNull
    private Object coordinates;
    // Find a better way of representing coordinates here. Maybe there is an interface in the hibernate-spatial dependency.
}
