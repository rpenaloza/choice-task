package com.encora.choice.core.entity;

import com.encora.choice.core.entity.Amenity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Hotel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private byte rating;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "hotel_amenity" , joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private Set<Amenity> amenities;
}
