package com.cashflow.commons.core.dto.request;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class BaseRequestTest {

    private final Long number = 1L;
    private final Locale locale = Locale.getDefault();
    private final String jwtToken = "jwt";

    @Test
    void allArgsConstructor() {
        BaseRequest<Long> baseRequest = new BaseRequest<>(jwtToken, locale, number);
        assertAll(() -> {
            assertEquals(number, baseRequest.getRequest());
            assertEquals(locale, baseRequest.getLanguage());
            assertEquals(jwtToken, baseRequest.getJwtToken());
        });
    }

    @Test
    void languageAndRequestConstructor() {
        BaseRequest<Long> baseRequest = new BaseRequest<>(locale, number);
        assertAll(() -> {
            assertEquals(number, baseRequest.getRequest());
            assertEquals(locale, baseRequest.getLanguage());
        });
    }
}