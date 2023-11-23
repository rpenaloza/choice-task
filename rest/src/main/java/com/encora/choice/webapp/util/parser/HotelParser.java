package com.encora.choice.webapp.util.parser;

import com.encora.choice.webapp.vo.Hotel;
import com.encora.choice.webapp.vo.SearchPage;
import com.encora.choice.webapp.ws.client.*;

public interface HotelParser {
    GetHotelRequest parseToHotelRequest(long id);

    CreateHotelRequest parseToCreateHotelRequest(Hotel hotel);

    UpdateHotelRequest parseToUpdateHotelRequest(Hotel hotel);

    DeleteHotelRequest parseToDeleteHotelRequest(long id);

    HotelSearchRequest parseToHotelSearchRequest(int page, int size, String term);

    Hotel parseToHotelVO(GetHotelResponse hotel);

    SearchPage parseToSearchPage(HotelSearchResponse res);

    AddAmenityRequest parseToAddAmenityRequest(long hotelId, long amenityId);

    RemoveAmenityRequest parseToRemoveAmenityRequest(long hotelId, long amenityId);
}
