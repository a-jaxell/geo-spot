package com.laboration2.category;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.laboration2.location.Location;
import com.laboration2.validation.EmojiSymbol;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    // Göra egen validation rule för emojisymboler. en annotation  @Emoji
    @EmojiSymbol
    @Size(max = 255)
    @Column(name = "symbol")
    private String symbol;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference(value = "category-locations")
    private Set<Location> locations = new LinkedHashSet<>();

}
