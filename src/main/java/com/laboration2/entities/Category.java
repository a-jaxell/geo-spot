package com.laboration2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category {
    @Id
    Long id;
    String name;
    String symbol;
    String description;

}
