package com.encora.choice.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Amenity {
    @Id
    private long id;
    private String name;
}
