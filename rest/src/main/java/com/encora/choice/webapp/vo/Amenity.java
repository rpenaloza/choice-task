package com.encora.choice.webapp.vo;

import lombok.NonNull;

import javax.validation.constraints.Min;

public class Amenity {
    @Min(1)
    @NonNull
    private Long id;
    private String name;
}
