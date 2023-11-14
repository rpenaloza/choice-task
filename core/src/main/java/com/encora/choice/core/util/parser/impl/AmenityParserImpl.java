package com.encora.choice.core.util.parser.impl;

import com.encora.choice.core.entity.Amenity;
import com.encora.choice.core.util.parser.AmenityParser;
import com.encora.choice.core.ws.hotelcatalog.AmenityDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AmenityParserImpl implements AmenityParser {

    private final ModelMapper mapper;

    public AmenityParserImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Amenity parseToEntity(AmenityDTO dto) {
        return mapper.map(dto, Amenity.class);
    }

    @Override
    public AmenityDTO parseToDTO(Amenity entity) {
        return mapper.map(entity, AmenityDTO.class);
    }

    @Override
    public Set<Amenity> parseToEntitySet(List<AmenityDTO> dtoList) {
        return dtoList.stream().map(this::parseToEntity).collect(Collectors.toSet());
    }

    @Override
    public List<AmenityDTO> parseToDTOList(Set<Amenity> entitySet) {
        return entitySet.stream().map(this::parseToDTO).collect(Collectors.toList());
    }
}
