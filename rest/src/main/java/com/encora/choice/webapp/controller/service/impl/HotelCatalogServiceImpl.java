package com.encora.choice.webapp.controller.service.impl;

import com.encora.choice.webapp.controller.service.HotelCatalogService;
import com.encora.choice.webapp.vo.Hotel;
import com.encora.choice.webapp.vo.SearchPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HotelCatalogServiceImpl implements HotelCatalogService {
    @Override
    public Hotel getHotel(long id) {
        return null;
    }

    @Override
    public long createHotel(Hotel hotel) {
        return 0;
    }

    @Override
    public void updateHotel(Hotel hotel) {

    }

    @Override
    public void deleteHotel(long id) {

    }

    @Override
    public SearchPage searchHotel(int page, int size, String term) {
        return null;
    }

    @Override
    public void addAmenity(long hotelId, long amenityId) {

    }

    @Override
    public void removeAmenity(long hotelId, long amenityId) {

    }
}
