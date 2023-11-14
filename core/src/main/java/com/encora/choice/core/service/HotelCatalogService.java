package com.encora.choice.core.service;

import com.encora.choice.core.ws.hotelcatalog.*;

public interface HotelCatalogService {

    CreateHotelResponse createHotel(CreateHotelRequest request);
    GetHotelResponse getHotel(GetHotelRequest request);
    void updateHotel(UpdateHotelRequest request);
    void deleteHotel(DeleteHotelRequest request);
    HotelSearchResponse searchHotels(HotelSearchRequest request);
    void addAmenity(AddAmenityRequest request);
    void removeAmenity(RemoveAmenityRequest request);

}
