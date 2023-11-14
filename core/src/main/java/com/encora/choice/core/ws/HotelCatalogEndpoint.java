package com.encora.choice.core.ws;

import com.encora.choice.core.service.HotelCatalogService;
import com.encora.choice.core.ws.hotelcatalog.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Slf4j
@Endpoint
public class HotelCatalogEndpoint {

    private final HotelCatalogService service;

    private static final String NAMESPACE = "http://www.encora.com/choice/core/ws/hotelCatalog";


    public HotelCatalogEndpoint(HotelCatalogService service) {
        this.service = service;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "CreateHotelRequest")
    @ResponsePayload
    public CreateHotelResponse createHotel(CreateHotelRequest request){
       log.info("Create hotel request");
        return service.createHotel(request);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "GetHotelRequest")
    @ResponsePayload
    public GetHotelResponse getHotel(GetHotelRequest request){
        log.info("Read hotel request");
        return service.getHotel(request);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "UpdateHotelRequest")
    @ResponsePayload
    public void updateHotel(UpdateHotelRequest request){
        log.info("update hotel request");
        service.updateHotel(request);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "DeleteHotelRequest")
    @ResponsePayload
    public void deleteHotel(DeleteHotelRequest request){
        log.info("Delete hotel request");
        service.deleteHotel(request);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "HotelSearchRequest")
    @ResponsePayload
    public HotelSearchResponse searchHotels(HotelSearchRequest request){
        log.info("Search hotel request");
        return service.searchHotels(request);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "AddAmenityRequest")
    @ResponsePayload
    public void addAmenity(AddAmenityRequest request){
        log.info("Add amenity request");
        service.addAmenity(request);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "RemoveAmenityRequest")
    @ResponsePayload
    public void removeAmenity(RemoveAmenityRequest request){
        log.info("Remove amenity request");
        service.removeAmenity(request);
    }
}
