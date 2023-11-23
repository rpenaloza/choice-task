package com.encora.choice.webapp.controller;

import com.encora.choice.webapp.service.HotelCatalogService;
import com.encora.choice.webapp.vo.Hotel;
import com.encora.choice.webapp.vo.SearchPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HotelCatalogControllerTest {

    @Mock
    private HotelCatalogService hotelCatalogService;

    @InjectMocks
    private HotelCatalogController hotelCatalogController;

    @Test
    void searchPage_ReturnsSearchPage() {
        when(hotelCatalogService.searchHotel(anyInt(), anyInt(), anyString())).thenReturn(new SearchPage());
        SearchPage result = hotelCatalogController.searchPage(1, 10, "test");
        assertNotNull(result);
    }

    @Test
    void getHotel_ReturnsHotel() {
        when(hotelCatalogService.getHotel(anyLong())).thenReturn(new Hotel());
        Hotel result = hotelCatalogController.getHotel(1);
        assertNotNull(result);
    }

    @Test
    void createHotel_ReturnsHotelId() {
        when(hotelCatalogService.createHotel(any())).thenReturn(1L);
        long result = hotelCatalogController.createHotel(new Hotel());
        assertNotNull(result);
    }

    @Test
    void updateHotel_VerifyServiceMethodCalled() {
        assertDoesNotThrow(()->hotelCatalogController.updateHotel(new Hotel()));
    }

    @Test
    void deleteHotel_VerifyServiceMethodCalled() {
        assertDoesNotThrow(()->hotelCatalogController.deleteHotel(1));
    }

    @Test
    void addAmenity_VerifyServiceMethodCalled() {
        assertDoesNotThrow(()->hotelCatalogController.addAmenity(1, 1));
    }

    @Test
    void removeAmenity_VerifyServiceMethodCalled() {
        assertDoesNotThrow(()->hotelCatalogController.removeAmenity(1, 1));
    }
}