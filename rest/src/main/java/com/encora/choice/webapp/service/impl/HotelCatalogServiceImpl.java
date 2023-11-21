package com.encora.choice.webapp.service.impl;

import com.encora.choice.webapp.service.HotelCatalogService;
import com.encora.choice.webapp.util.parser.HotelParser;
import com.encora.choice.webapp.vo.Hotel;
import com.encora.choice.webapp.vo.SearchPage;
import com.encora.choice.webapp.ws.client.GetHotelResponse;
import com.encora.choice.webapp.ws.client.HotelCatalogPort;
import com.encora.choice.webapp.ws.client.HotelSearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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
        log.info("Performing soap request for createHotel");
        wsClient.createHotel(parser.parseToCreateHotelRequest(hotel));
        return 0;
    }

    @Override
    public void updateHotel(Hotel hotel) {
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