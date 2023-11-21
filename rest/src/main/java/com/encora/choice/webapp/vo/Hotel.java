package com.encora.choice.webapp.vo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Set;

@Data
public class Hotel {
    @Min(value = 1,message = "Id must be a positive value")
    private Long id;
    @Size(min = 3, max = 75, message = "name must have a length between 3 and 75")
    private String name;
    @Size(min = 10, max = 250, message = "address must have a length between 10 and 250")
    private String address;
    @Min(1)
    @Max(5)
    private byte rating;
    @Valid
    private Set<Amenity> amenities;
}
