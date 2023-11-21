package com.encora.choice.core.util.parser.impl;

import com.encora.choice.core.entity.Hotel;
import com.encora.choice.core.util.parser.AmenityParser;
import com.encora.choice.core.util.parser.HotelParser;
import com.encora.choice.core.ws.hotelcatalog.HotelDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        log.debug("Parsing to Hotel DTO");
        log.trace(entity.toString());
        HotelDTO result =  mapper.map(entity,HotelDTO.class);
        result.getAmenity().addAll(amenityParser.parseToDTOList(entity.getAmenities()));
        log.debug("Successfully parsed to DTO");
        return result;
    }

    @Override
    public Hotel parseToEntity(HotelDTO dto) {
        log.debug("Parsing to Hotel Entity");
        log.trace(dto.toString());
        Hotel result =  mapper.map(dto,Hotel.class);
        if (dto.getId() == 0) {
            result.setId(null);
        }
        result.setAmenities(amenityParser.parseToEntitySet(dto.getAmenity()));
        log.debug("Successfully parsed to entity");
        return result;
    }

    @Override
    public List<HotelDTO> parseToDTOList(List<Hotel> entities) {
        log.debug("Parsing to Hotel Entity List");
        return entities.stream().map(this::parseToDTO).collect(Collectors.toList());
    }
}
