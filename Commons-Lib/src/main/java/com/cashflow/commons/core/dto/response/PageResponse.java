package com.cashflow.commons.core.dto.response;

import java.util.List;

/**
 *
 * Basic generic object to serve as a pagination response.
 *
 * @author Vinicius Peralta
 * @since 0.0.4
 *
 */
public class PageResponse<T> {

    private final List<T> response;
    private final int pageNumber;
    private final int pageSize;
    private final long totalElements;
    private final int totalPages;

    public PageResponse(List<T> response, int pageNumber, int pageSize, long totalElements, int totalPages) {
        this.response = response;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public List<T> getResponse() {
        return response;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
