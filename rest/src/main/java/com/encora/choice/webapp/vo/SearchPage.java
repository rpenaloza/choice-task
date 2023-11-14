package com.encora.choice.webapp.vo;

import lombok.Data;

import java.util.List;

@Data
public class SearchPage {
    private int currentPage;
    private int currentSize;
    private int totalPages;
    private long totalCount;
    private List<Hotel> hotels;
}
