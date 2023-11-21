package com.encora.choice.webapp.util.parser.impl;

import com.encora.choice.webapp.util.parser.HotelParser;
import com.encora.choice.webapp.vo.Amenity;
import com.encora.choice.webapp.vo.Hotel;
import com.encora.choice.webapp.vo.SearchPage;
import com.encora.choice.webapp.ws.client.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class HotelParserImpl implements HotelParser {

    private final ModelMapper mapper;

    public HotelParserImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public GetHotelRequest parseToHotelRequest(long id) {
        GetHotelRequest res = new GetHotelRequest();
        res.setId(id);
        return res;
    }

    @Override
    public CreateHotelRequest parseToCreateHotelRequest(Hotel hotel) {
        CreateHotelRequest res = new CreateHotelRequest();
        res.setHotel(parseToHotelDTO(hotel));
        return res;
    }

    @Override
    public UpdateHotelRequest parseToUpdateHotelRequest(Hotel hotel) {
        UpdateHotelRequest res = new UpdateHotelRequest();
        res.setHotel(parseToHotelDTO(hotel));
        return res;
    }

    @Override
    public DeleteHotelRequest parseToDeleteHotelRequest(long id) {
        DeleteHotelRequest res = new DeleteHotelRequest();
        res.setId(id);
        return res;
    }

    @Override
    public HotelSearchRequest parseToHotelSearchRequest(int page, int size, String term) {
        HotelSearchRequest res = new HotelSearchRequest();
        res.setPage(page);
        res.setSize(size);
        res.setTerm(term);
        return res;
    }

    @Override
    public Hotel parsToHotelVO(GetHotelResponse dto) {
        return parseToHotelVO(dto.getHotel());
    }

    @Override
    public SearchPage parseToSearchPage(HotelSearchResponse page) {
        SearchPage res = new SearchPage();
        res.setCurrentPage(page.getCurrentPage());
        res.setTotalPages(page.getTotalPages());
        res.setCurrentSize(page.getCurrentSize());
        res.setTotalCount(page.getTotalCount());
        res.setHotels(parseToHotelList(page.getHotel()));
        return res;
    }


    @Override
    public AddAmenityRequest parseToAddAmenityRequest(long hotelId, long amenityId) {
        AddAmenityRequest res = new AddAmenityRequest();
        res.setHotelId(hotelId);
        res.setAmenityId(amenityId);
        return res;
    }

    @Override
    public RemoveAmenityRequest parseToRemoveAmenityRequest(long hotelId, long amenityId) {
        RemoveAmenityRequest res = new RemoveAmenityRequest();
        res.setHotelId(hotelId);
        res.setAmenityId(amenityId);
        return res;
    }
    private List<Hotel> parseToHotelList(List<HotelDTO> hotels) {
       return hotels.stream().map(this::parseToHotelVO).collect(Collectors.toList());
    }

    private HotelDTO parseToHotelDTO(Hotel vo){
        HotelDTO hotel = mapper.map(vo, HotelDTO.class);
        hotel.getAmenity().addAll(parseToAmenityDTOList(vo.getAmenities()));
        return hotel;
    }

    private Hotel parseToHotelVO(HotelDTO dto){
        Hotel hotel = mapper.map(dto, Hotel.class);
        hotel.setAmenities(parseToAmenityVoSet(dto.getAmenity()));
        return hotel;
    }

    private Set<Amenity> parseToAmenityVoSet(List<AmenityDTO> amenities) {
        return amenities.stream().map(this::pareToAmenityVO).collect(Collectors.toSet());
    }

    private Amenity pareToAmenityVO(AmenityDTO dto) {
        return mapper.map(dto,Amenity.class);
    }

    private AmenityDTO parseToAmenityDTO(Amenity vo){
        return mapper.map(vo,AmenityDTO.class);
    }

    private List<AmenityDTO> parseToAmenityDTOList(Set<Amenity> vos){
        if (Optional.ofNullable(vos).isPresent()) {
            return vos.stream().map(this::parseToAmenityDTO).collect(Collectors.toList());
        }else{
            return new ArrayList<>();
        }
    }
}
