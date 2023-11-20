package com.encora.choice.core.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Amenity {
    @Id
    private Long id;
    private String name;
}
