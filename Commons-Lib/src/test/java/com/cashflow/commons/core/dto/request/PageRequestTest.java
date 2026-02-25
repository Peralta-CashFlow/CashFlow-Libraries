package com.cashflow.commons.core.dto.request;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class PageRequestTest {

    private final int pageNumber = 1;
    private final int pageSize = 10;
    private final String search = "search";
    private final Locale languague = Locale.CANADA;
    private final Long request = 1L;
    private final Long userId = 123L;

    @Test
    void testConstructor() {
        PageRequest<Void> pageRequest = new PageRequest<>(pageNumber, pageSize, languague, search, userId);
        assertAll(() -> {
            assertEquals(pageNumber, pageRequest.getPageable().getPageNumber());
            assertEquals(pageSize, pageRequest.getPageable().getPageSize());
            assertEquals(languague, pageRequest.getLanguage());
            assertEquals(search, pageRequest.getSearch());
            assertEquals(userId, pageRequest.getUserId());
            assertNull(pageRequest.getRequest());
        });
    }

    @Test
    void testAllArgsConstructor() {
        PageRequest<Long> pageRequest = new PageRequest<>(pageNumber, pageSize, languague, search, request, userId);
        assertAll(() -> {
            assertEquals(pageNumber, pageRequest.getPageable().getPageNumber());
            assertEquals(pageSize, pageRequest.getPageable().getPageSize());
            assertEquals(search, pageRequest.getSearch());
            assertEquals(languague, pageRequest.getLanguage());
            assertEquals(request, pageRequest.getRequest());
            assertEquals(userId, pageRequest.getUserId());
        });
    }

}