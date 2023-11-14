package com.encora.choice.webapp.controller;

import com.encora.choice.webapp.controller.service.HotelCatalogService;
import com.encora.choice.webapp.vo.Hotel;
import com.encora.choice.webapp.vo.SearchPage;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Slf4j
@Validated
@RestController
@RequestMapping("/hotel")
public class HotelCatalogController {


    private final HotelCatalogService service;

    public HotelCatalogController(HotelCatalogService service) {
        this.service = service;
    }

    @GetMapping
    public SearchPage searchPage(@RequestParam @Min(1) Integer page, @RequestParam @Min(0) Integer size,
                                 @RequestParam @Size(min = 3) String searchTerm) {
        log.info("Performing search request for page:" + page + " size:" + size + " and term:" + searchTerm);
        return service.searchHotel(page,size,searchTerm);
    }

    @GetMapping("/{id}")
    public Hotel getHotel(@PathVariable @Min(1) long id) {
        log.info("Performing get request for hotel with id:"+id);
        return service.getHotel(id);
    }

    @PostMapping
    public long createHotel(@RequestBody @NonNull @Valid Hotel hotel) {
        log.info("Performing create request for hotel");
        log.debug(hotel.toString());
        log.debug(hotel.toString());
        return service.createHotel(hotel);
    }

    @PutMapping
    public void updateHotel(@RequestBody @NonNull @Valid Hotel hotel) {
        log.info("Performing update request for hotel");
        log.debug(hotel.toString());
        service.updateHotel(hotel);
    }

    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable @Min(1) long id) {
        log.info("Performing update request for hotel with id:"+id);
        service.deleteHotel(id);
    }

    @PostMapping("/{hotelId}/amenity/{amenityId}")
    public void addAmenity(@PathVariable @Min(1)long hotelId, @PathVariable @Min(1)long amenityId) {
        log.info("Performing request to add amenity with id "+amenityId+" to hotel with id "+hotelId);
        service.addAmenity(hotelId,amenityId);
    }

    @DeleteMapping("/{hotelId}/amenity/{amenityId}")
    public void removeAmenity(@PathVariable @Min(1)long hotelId, @PathVariable @Min(1)long amenityId) {
        log.info("Performing request to remove amenity with id "+amenityId+" from hotel with id "+hotelId);
        service.removeAmenity(hotelId,amenityId);
    }
}
