package com.encora.choice.core.util.parser.impl;

import com.encora.choice.core.entity.Hotel;
import com.encora.choice.core.util.parser.AmenityParser;
import com.encora.choice.core.util.parser.HotelParser;
import com.encora.choice.core.ws.hotelcatalog.HotelDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelParserImpl implements HotelParser {

    private final ModelMapper mapper;

    private final AmenityParser amenityParser;

    public HotelParserImpl(ModelMapper mapper, AmenityParser amenityParser) {
        this.mapper = mapper;
        this.amenityParser = amenityParser;
    }

    @Override
    public HotelDTO parseToDTO(Hotel entity) {
        HotelDTO result =  mapper.map(entity,HotelDTO.class);
        result.getAmenity().addAll(amenityParser.parseToDTOList(entity.getAmenities()));
        return result;
    }

    @Override
    public Hotel parseToEntity(HotelDTO dto) {
        Hotel result =  mapper.map(dto,Hotel.class);
        if (dto.getId() == 0) {
            result.setId(null);
        }
        result.setAmenities(amenityParser.parseToEntitySet(dto.getAmenity()));
        return result;
    }

    @Override
    public List<HotelDTO> parseToDTOList(List<Hotel> entities) {
        return entities.stream().map(this::parseToDTO).collect(Collectors.toList());
    }
}
