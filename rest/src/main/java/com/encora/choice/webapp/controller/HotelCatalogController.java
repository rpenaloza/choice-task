package com.encora.choice.webapp.controller;

import com.encora.choice.webapp.service.HotelCatalogService;
import com.encora.choice.webapp.vo.Hotel;
import com.encora.choice.webapp.vo.SearchPage;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Optional;

/**
 * Controller to expose Hotel Catalog Operations
 */
@Log4j
@Validated
@RestController
@RequestMapping("/hotel")
public class HotelCatalogController {


    private final HotelCatalogService service;

    @Value("${api.search.defaultPageSize}")
    private Integer defaultPageSize;

    public HotelCatalogController(HotelCatalogService service) {
        this.service = service;
    }


    @PostConstruct
    public void init(){
        log.info("default from properties "+ defaultPageSize);
    }
    @GetMapping
    public SearchPage searchPage(@RequestParam @Min(0) Integer page, @RequestParam @Min(1) Integer size, @RequestParam @Size(min = 3) @Pattern(regexp = "/^[\\w\\-\\s]+$/") String searchTerm) {
        log.info("Performing search request for page:" + page + " size:" + size + " and term:" + searchTerm);
        return service.searchHotel(Optional.of(page).orElse(0), Optional.of(size).orElse(defaultPageSize), searchTerm);
    }

    @GetMapping("/{id}")
    public Hotel getHotel(@PathVariable @Min(1) long id) {
        System.out.println("Performing get request for hotel with id:" + id);
       log.info(
               "Performing get request for hotel with id:" + id);
        return service.getHotel(id);
    }

    @PostMapping
    public long createHotel(@RequestBody @NotNull @Valid Hotel hotel) {
        log.info("Performing create request for hotel");
        log.debug(hotel.toString());
        return service.createHotel(hotel);
    }

    @PutMapping
    public void updateHotel(@RequestBody @NotNull @Valid Hotel hotel) {
        log.info("Performing update request for hotel");
        log.debug(hotel.toString());
        service.updateHotel(hotel);
    }

    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable @Min(1) long id) {
        System.out.println("Performing update request for hotel with id:" + id);
        service.deleteHotel(id);
    }

    @PostMapping("/{hotelId}/amenity/{amenityId}")
    public void addAmenity(@PathVariable @Min(1) long hotelId, @PathVariable @Min(1) long amenityId) {
        log.info("Performing request to add amenity with id " + amenityId + " to hotel with id " + hotelId);
        service.addAmenity(hotelId, amenityId);
    }

    @DeleteMapping("/{hotelId}/amenity/{amenityId}")
    public void removeAmenity(@PathVariable @Min(1) long hotelId, @PathVariable @Min(1) long amenityId) {
        log.info("Performing request to remove amenity with id " + amenityId + " from hotel with id " + hotelId);
        service.removeAmenity(hotelId, amenityId);
    }
}
