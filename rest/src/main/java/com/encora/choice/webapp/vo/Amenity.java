package com.encora.choice.webapp.vo;


import lombok.Data;

import javax.validation.constraints.*;
@Data
public class Amenity {
    @Min(1)
    @NotNull
    private Long id;
    private String name;
}
