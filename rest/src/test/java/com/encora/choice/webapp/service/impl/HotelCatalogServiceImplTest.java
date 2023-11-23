package com.encora.choice.webapp.service.impl;

import com.encora.choice.webapp.util.parser.HotelParser;
import com.encora.choice.webapp.vo.Hotel;
import com.encora.choice.webapp.vo.SearchPage;
import com.encora.choice.webapp.ws.client.CreateHotelResponse;
import com.encora.choice.webapp.ws.client.GetHotelResponse;
import com.encora.choice.webapp.ws.client.HotelCatalogPort;
import com.encora.choice.webapp.ws.client.HotelSearchResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class HotelCatalogServiceImplTest {

    @Mock
    private HotelCatalogPort wsClient;

    @Mock
    private HotelParser parser;

    @InjectMocks
    private HotelCatalogServiceImpl hotelCatalogService;

    @Test
    void getHotel_ReturnsHotel() {
        long hotelId = 1L;
        GetHotelResponse getHotelResponse = new GetHotelResponse();
        when(wsClient.getHotel(any())).thenReturn(getHotelResponse);
        when(parser.parseToHotelVO(any())).thenReturn(new Hotel());
        Hotel result = hotelCatalogService.getHotel(hotelId);
        assertNotNull(result);
    }

    @Test
    void createHotel_ReturnsHotelId() {
        Hotel hotel = new Hotel();
        CreateHotelResponse createHotelResponse = new CreateHotelResponse();
        createHotelResponse.setHotelId(1L);
        when(wsClient.createHotel(any())).thenReturn(createHotelResponse);
        long result = hotelCatalogService.createHotel(hotel);
        assertEquals(1L, result);
    }

    @Test
    void createHotel_WithNonNullId_ThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        assertThrows(IllegalArgumentException.class, () -> hotelCatalogService.createHotel(hotel));
    }

    @Test
    void updateHotel_WithNullId_ThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel();
        assertThrows(IllegalArgumentException.class, () -> hotelCatalogService.updateHotel(hotel));
    }

    @Test
    void deleteHotel_CallsWsClientDeleteHotel() {
        long hotelId = 1L;
        hotelCatalogService.deleteHotel(hotelId);
        verify(wsClient, times(1)).deleteHotel(any());
    }

    @Test
    void searchHotel_ReturnsSearchPage() {
        HotelSearchResponse searchResponse = new HotelSearchResponse();
        when(wsClient.hotelSearch(any())).thenReturn(searchResponse);
        when(parser.parseToSearchPage(any())).thenReturn(new SearchPage(/* Mock data for SearchPage */));
        SearchPage result = hotelCatalogService.searchHotel(1, 10, "test");
        assertNotNull(result);
    }

    @Test
    void addAmenity_CallsWsClientAddAmenity() {
        long hotelId = 1L;
        long amenityId = 2L;
        hotelCatalogService.addAmenity(hotelId, amenityId);
        verify(wsClient, times(1)).addAmenity(any());
    }

    @Test
    void removeAmenity_CallsWsClientRemoveAmenity() {
        long hotelId = 1L;
        long amenityId = 2L;
        hotelCatalogService.removeAmenity(hotelId, amenityId);
        verify(wsClient, times(1)).removeAmenity(any());
    }
}
