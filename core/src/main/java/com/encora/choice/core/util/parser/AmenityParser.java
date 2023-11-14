package com.encora.choice.core.util.parser;

import com.encora.choice.core.entity.Amenity;
import com.encora.choice.core.ws.hotelcatalog.AmenityDTO;

import java.util.List;
import java.util.Set;

public interface AmenityParser {
    Amenity parseToEntity(AmenityDTO dto);
    AmenityDTO parseToDTO(Amenity entity);

    Set<Amenity> parseToEntitySet(List<AmenityDTO> dtoList);

    List<AmenityDTO> parseToDTOList(Set<Amenity> entitySet);

}
