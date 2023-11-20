package com.encora.choice.webapp.service;

import com.encora.choice.webapp.vo.Hotel;
import com.encora.choice.webapp.vo.SearchPage;

public interface HotelCatalogService {

    Hotel getHotel (long id);
    long createHotel(Hotel hotel);
    void updateHotel(Hotel hotel);
    void deleteHotel(long id);
    SearchPage searchHotel(int page, int size, String term);

    void addAmenity(long hotelId, long amenityId);
    void removeAmenity(long hotelId, long amenityId);
}
