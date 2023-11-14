package com.encora.choice.core.repository;

import com.encora.choice.core.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HotelPaginationDAO extends PagingAndSortingRepository<Hotel,Long> {

    Page<Hotel> findAllByNameLikeIgnoreCase(String term, Pageable pageable);

}