package com.encora.choice.webapp.service.impl;

import com.encora.choice.webapp.service.HotelCatalogService;
import com.encora.choice.webapp.util.parser.HotelParser;
import com.encora.choice.webapp.vo.Hotel;
import com.encora.choice.webapp.vo.SearchPage;
import com.encora.choice.webapp.ws.client.CreateHotelResponse;
import com.encora.choice.webapp.ws.client.GetHotelResponse;
import com.encora.choice.webapp.ws.client.HotelCatalogPort;
import com.encora.choice.webapp.ws.client.HotelSearchResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 * Business Logic and validations for hotel catalog
 */
@Log4j
@Service
public class HotelCatalogServiceImpl implements HotelCatalogService {

    public HotelCatalogServiceImpl(HotelCatalogPort wsClient, HotelParser parser) {
        this.wsClient = wsClient;
        this.parser = parser;
    }

    private final HotelCatalogPort wsClient;
    private final HotelParser parser;
    @Override
    public Hotel getHotel(long id) {
        log.info("Performing soap request for getHotel");
        GetHotelResponse hotel = wsClient.getHotel(parser.parseToHotelRequest(id));
        return parser.parsToHotelVO(hotel);
    }

    @Override
    public long createHotel(Hotel hotel) {
        if (hotel.getId()!=null) throw new IllegalArgumentException("Hotel id must be null for creation");
        log.info("Performing soap request for createHotel");
        CreateHotelResponse res = wsClient.createHotel(parser.parseToCreateHotelRequest(hotel));
        log.info("Successfully created hotel with id "+res.getHotelId());
        return res.getHotelId();
    }

    @Override
    public void updateHotel(Hotel hotel) {
        if (hotel.getId()==null) throw new IllegalArgumentException("Hotel id can't be null for update");
        log.info("Performing soap request to updateHotel");
        wsClient.updateHotel(parser.parseToUpdateHotelRequest(hotel));
    }

    @Override
    public void deleteHotel(long id) {
        log.info("Performing soap request to delete hotel");
        wsClient.deleteHotel(parser.parseToDeleteHotelRequest(id));
    }

    @Override
    public SearchPage searchHotel(int page, int size, String term) {
        log.info("Performing soap request to searchHotel");
         HotelSearchResponse res =wsClient.hotelSearch(parser.parseToHotelSearchRequest(page,size,term));
        return parser.parseToSearchPage(res);
    }

    @Override
    public void addAmenity(long hotelId, long amenityId) {
        log.info("Performing soap request to addAmenity");
        wsClient.addAmenity(parser.parseToAddAmenityRequest(hotelId,amenityId));
    }

    @Override
    public void removeAmenity(long hotelId, long amenityId) {
        log.info("Performing soap request to removeAmenity");
        wsClient.removeAmenity(parser.parseToRemoveAmenityRequest(hotelId,amenityId));
    }
}
