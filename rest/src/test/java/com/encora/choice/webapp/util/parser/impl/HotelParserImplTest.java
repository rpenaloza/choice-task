package com.encora.choice.webapp.util.parser.impl;

import com.encora.choice.webapp.vo.Hotel;
import com.encora.choice.webapp.vo.SearchPage;
import com.encora.choice.webapp.ws.client.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class HotelParserImplTest {


    private static HotelParserImpl hotelParser;

    @BeforeAll
    static void setup(){
        ModelMapper mapper = new ModelMapper();
        hotelParser= new HotelParserImpl(mapper);
    }

    @Test
    void parseToHotelRequest_ReturnsGetHotelRequest() {
        long id = 1L;
        GetHotelRequest result = hotelParser.parseToHotelRequest(id);
        assertEquals(id, result.getId());
    }

    @Test
    void parseToCreateHotelRequest_ReturnsCreateHotelRequest() {
        Hotel hotel = new Hotel(/* Mock data for Hotel */);
        CreateHotelRequest result = hotelParser.parseToCreateHotelRequest(hotel);
        assertNotNull(result);
    }

    @Test
    void parseToUpdateHotelRequest_ReturnsUpdateHotelRequest() {
        Hotel hotel = new Hotel(/* Mock data for Hotel */);
        hotel.setAmenities(new HashSet<>());
        UpdateHotelRequest result = hotelParser.parseToUpdateHotelRequest(hotel);
        assertNotNull(result);
    }

    @Test
    void parseToDeleteHotelRequest_ReturnsDeleteHotelRequest() {
        long id = 1L;
        DeleteHotelRequest result = hotelParser.parseToDeleteHotelRequest(id);
        assertEquals(id, result.getId());
    }

    @Test
    void parseToHotelSearchRequest_ReturnsHotelSearchRequest() {
        int page = 1;
        int size = 10;
        String term = "test";
        HotelSearchRequest result = hotelParser.parseToHotelSearchRequest(page, size, term);
        assertEquals(page, result.getPage());
        assertEquals(size, result.getSize());
        assertEquals(term, result.getTerm());
    }

    @Test
    void parseToHotelVO_ReturnsHotel() {
        GetHotelResponse dto = new GetHotelResponse();
        HotelDTO hotelDTO = new HotelDTO();
        dto.setHotel(hotelDTO);
//        when(mapper.map(any(), eq(Hotel.class))).thenReturn(new Hotel());
        Hotel result = hotelParser.parseToHotelVO(dto);
        assertNotNull(result);
    }

    @Test
    void parseToSearchPage_ReturnsSearchPage() {
        HotelSearchResponse page = new HotelSearchResponse();
        List<HotelDTO> hotelDTOList = Collections.singletonList(new HotelDTO());
        page.getHotel().addAll(hotelDTOList);
//        when(mapper.map(any(), eq(SearchPage.class))).thenReturn(new SearchPage());
        SearchPage result = hotelParser.parseToSearchPage(page);
        assertNotNull(result);
    }

    @Test
    void parseToAddAmenityRequest_ReturnsAddAmenityRequest() {
        long hotelId = 1L;
        long amenityId = 2L;
        AddAmenityRequest result = hotelParser.parseToAddAmenityRequest(hotelId, amenityId);
        assertEquals(hotelId, result.getHotelId());
        assertEquals(amenityId, result.getAmenityId());
    }

    @Test
    void parseToRemoveAmenityRequest_ReturnsRemoveAmenityRequest() {
        long hotelId = 1L;
        long amenityId = 2L;
        RemoveAmenityRequest result = hotelParser.parseToRemoveAmenityRequest(hotelId, amenityId);
        assertEquals(hotelId, result.getHotelId());
        assertEquals(amenityId, result.getAmenityId());
    }

}
