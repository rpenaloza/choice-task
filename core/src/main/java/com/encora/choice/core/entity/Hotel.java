package com.encora.choice.core.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Hotel {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String address;
    private byte rating;
    @ManyToMany
    @JoinTable(name = "hotel_amenity" , joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private Set<Amenity> amenities;
}
