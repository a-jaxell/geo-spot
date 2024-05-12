package com.laboration2.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.laboration2.location.Location;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 255)
    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "user-locations")
    private List<Location> locations;
}