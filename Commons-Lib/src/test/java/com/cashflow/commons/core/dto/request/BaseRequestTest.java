package com.cashflow.commons.core.dto.request;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class BaseRequestTest {

    private final Long number = 1L;
    private final Locale locale = Locale.getDefault();
    private final Long userId = 123L;

    @Test
    void allArgsConstructor() {
        BaseRequest<Long> baseRequest = new BaseRequest<>(locale, number, userId);
        assertAll(() -> {
            assertEquals(number, baseRequest.getRequest());
            assertEquals(locale, baseRequest.getLanguage());
            assertEquals(userId, baseRequest.getUserId());
        });
    }

}