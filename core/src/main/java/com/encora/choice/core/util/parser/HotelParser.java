package com.encora.choice.core.util.parser;

import com.encora.choice.core.entity.Hotel;
import com.encora.choice.core.ws.hotelcatalog.HotelDTO;

import java.util.List;

public interface HotelParser {

    HotelDTO parseToDTO(Hotel entity);
    Hotel parseToEntity(HotelDTO dto);

    List<HotelDTO> parseToDTOList(List<Hotel> entities);
}
