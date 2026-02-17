package com.cashflow.commons.core.dto.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PageResponseTest {

    @Test
    void constructorAndGetters() {

        List<String> items = List.of("item1", "item2");

        PageResponse<String> pageResponse = new PageResponse<>(
                items,
                1,
                10,
                20,
                2
        );

        assertEquals(items, pageResponse.getResponse());
        assertEquals(1, pageResponse.getPageNumber());
        assertEquals(10, pageResponse.getPageSize());
        assertEquals(20, pageResponse.getTotalElements());
        assertEquals(2, pageResponse.getTotalPages());
    }

}