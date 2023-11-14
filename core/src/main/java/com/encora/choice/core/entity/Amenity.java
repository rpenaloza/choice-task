package com.encora.choice.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Amenity {
    @Id
    private Long id;
    private String name;
}
