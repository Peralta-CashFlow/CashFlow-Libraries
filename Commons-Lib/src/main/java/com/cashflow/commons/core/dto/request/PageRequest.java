package com.cashflow.commons.core.dto.request;

import org.springframework.data.domain.Pageable;

import java.util.Locale;

/**
 *
 * Basic generic object to serve as a pagination request.
 *
 * @author Vinicius Peralta
 * @since 0.0.3
 *
 */
public class PageRequest<T> {

    private final Pageable pageable;
    private final String search;
    private final T request;
    private final Locale language;
    private final long userId;

    public PageRequest(int pageNumber, int pageSize, Locale language, String search, long userId) {
        this.pageable = org.springframework.data.domain.PageRequest.of(pageNumber, pageSize);
        this.language = language;
        this.search = search;
        this.userId = userId;
        this.request = null;
    }

    public PageRequest(int pageNumber, int pageSize, Locale language, String search, T request, long userId) {
        this.pageable = org.springframework.data.domain.PageRequest.of(pageNumber, pageSize);
        this.language = language;
        this.search = search;
        this.request = request;
        this.userId = userId;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public String getSearch() {
        return search;
    }

    public T getRequest() {
        return request;
    }

    public Locale getLanguage() {
        return language;
    }

    public long getUserId() { return userId; }
}
