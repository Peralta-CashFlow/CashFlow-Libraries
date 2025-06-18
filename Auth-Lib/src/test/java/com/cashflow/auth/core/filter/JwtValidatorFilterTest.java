package com.cashflow.auth.core.filter;

import com.cashflow.auth.core.service.jwt.CashFlowJwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JwtValidatorFilterTest {

    private JwtValidatorFilter filter;
    private final String[] notFilteredEndpoints = {"/public"};

    @BeforeEach
    void setUp() {
        CashFlowJwtService cashFlowJwtService = mock(CashFlowJwtService.class);
        filter = new JwtValidatorFilter(cashFlowJwtService, notFilteredEndpoints);
    }

    @ParameterizedTest
    @CsvSource({
            "/public,true",
            "/swagger-ui,true",
            "/actuator/health,true",
            "/api/secure,false"
    })
    void testShouldNotFilter_variousEndpoints(String path, boolean expected) {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getServletPath()).thenReturn(path);
        assertEquals(expected, filter.shouldNotFilter(request));
    }

    @Test
    void testShouldNotFilter_doesNotMatch() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getServletPath()).thenReturn("/api/secure");
        assertFalse(filter.shouldNotFilter(request));
    }
}