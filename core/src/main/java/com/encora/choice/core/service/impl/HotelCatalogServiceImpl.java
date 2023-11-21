package com.encora.choice.core.service.impl;

import com.encora.choice.core.entity.Amenity;
import com.encora.choice.core.entity.Hotel;
import com.encora.choice.core.repository.AmenityDAO;
import com.encora.choice.core.repository.HotelDAO;
import com.encora.choice.core.repository.HotelPaginationDAO;
import com.encora.choice.core.service.HotelCatalogService;
import com.encora.choice.core.util.StringUtils;
import com.encora.choice.core.util.parser.HotelParser;
import com.encora.choice.core.ws.exception.NoSuchAmenityException;
import com.encora.choice.core.ws.exception.NoSuchHotelException;
import com.encora.choice.core.ws.hotelcatalog.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
public class HotelCatalogServiceImpl implements HotelCatalogService {

    private final AmenityDAO amenityDAO;
    private final HotelDAO hotelDAO;
    private final HotelPaginationDAO hotelPaginationDAO;
    private final HotelParser hotelParser;

    private final ModelMapper mapper;

    private final StringUtils stringUtils;

    public HotelCatalogServiceImpl(AmenityDAO amenityDAO, HotelDAO hotelDAO, HotelPaginationDAO hotelPaginationDAO, HotelParser hotelParser, @Qualifier("skipNullMapper") ModelMapper mapper, StringUtils stringUtils) {
        this.amenityDAO = amenityDAO;
        this.hotelDAO = hotelDAO;
        this.hotelPaginationDAO = hotelPaginationDAO;
        this.hotelParser = hotelParser;
        this.mapper = mapper;
        this.stringUtils = stringUtils;
    }

    @Override
    public CreateHotelResponse createHotel(CreateHotelRequest request) {
        log.info("creating a new hotel");
        Hotel entity = hotelParser.parseToEntity(request.getHotel());
        long id = hotelDAO.save(entity).getId();
        log.info("successfully created hotel with id {}", id);
        CreateHotelResponse response = new CreateHotelResponse();
        response.setHotelId(id);
        return response;
    }

    @Override
    public GetHotelResponse getHotel(GetHotelRequest request) {
        log.info("obtaining hotel with id {}", request.getId());
        Hotel entity = hotelDAO.findById(request.getId()).orElseThrow(NoSuchHotelException::new);
        log.info("successfully obtained hotel with id {}", entity.getId());
        GetHotelResponse response = new GetHotelResponse();
        response.setHotel(hotelParser.parseToDTO(entity));
        return response;
    }

    @Override
    public void updateHotel(UpdateHotelRequest request) {
        log.info("updating hotel with id {}", request.getHotel().getId());
        Hotel newHotel = hotelParser.parseToEntity(request.getHotel());
        Hotel originalHotel = hotelDAO.findById(request.getHotel().getId()).orElseThrow(NoSuchHotelException::new);
        log.info("Successfully obtained hotel with id {}, proceeding to merge properties ", originalHotel.getId());
        //TODO verify amenities are copied
        mapper.map(newHotel,originalHotel);
        hotelDAO.save(originalHotel);
        log.info("Successfully update hotel with id {}", originalHotel.getId());
    }

    @Override
    public void deleteHotel(DeleteHotelRequest request) {
        log.info("deleting hotel with id {}", request.getId());
        hotelDAO.deleteById(request.getId());
        log.info("successfully deleted hotel with id {}", request.getId());
    }

    @Override
    public HotelSearchResponse searchHotels(HotelSearchRequest request) {
        log.info("processing Hotel Search request for page {} and size {}", request.getPage(), request.getSize());
        Page<Hotel> page;
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());
        if (request.getTerm() != null && !request.getTerm().isEmpty()) {
            String term = stringUtils.getSearchTerm(request.getTerm());
            log.info("searching by term {}",term);
            page = hotelPaginationDAO.findAllByNameLikeIgnoreCase(term, pageRequest);
            log.info("found {} hotels by search term",page.getContent().size());
        } else {
            log.info("searching hotels without term");
            page = hotelPaginationDAO.findAll(pageRequest);
        }
        HotelSearchResponse response = new HotelSearchResponse();
        response.setCurrentPage(request.getPage());
        response.setCurrentSize(request.getSize());
        response.setTotalCount(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.getHotel().addAll(hotelParser.parseToDTOList(page.getContent()));
        log.info("successfully completed Hotel Search");
        return response;
    }

    @Override
    public void addAmenity(AddAmenityRequest request) {
        log.info("Processing request to add amenity with id {} to hotel with id {}", request.getAmenityId(),request.getHotelId());
        Amenity amenity = amenityDAO.findById(request.getAmenityId()).orElseThrow(NoSuchAmenityException::new);
        Hotel hotel = hotelDAO.findById(request.getHotelId()).orElseThrow(NoSuchHotelException::new);
        hotel.getAmenities().add(amenity);
        hotelDAO.save(hotel);
        log.info("successfully processed addAmenity");
    }

    @Override
    public void removeAmenity(RemoveAmenityRequest request) {
        log.info("Processing request to add remove with id {} to hotel with id {}", request.getAmenityId(),request.getHotelId());
        Amenity amenity = amenityDAO.findById(request.getAmenityId()).orElseThrow(NoSuchAmenityException::new);
        Hotel hotel = hotelDAO.findById(request.getHotelId()).orElseThrow(NoSuchHotelException::new);
        hotel.getAmenities().remove(amenity);
        hotelDAO.save(hotel);
        log.info("successfully processed addAmenity");
    }
}
