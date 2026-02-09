package com.cashflow.auth.core.domain.authentication;

import com.cashflow.auth.core.domain.enums.RoleEnum;
import com.cashflow.auth.core.templates.CashFlowAuthenticationTemplates;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CashFlowAuthenticationTest {

    private static final List<RoleEnum> roles = List.of(RoleEnum.CASH_FLOW_BASICS);

    private static final CashFlowAuthentication cashFlowAuthentication = CashFlowAuthenticationTemplates.cashFlowAuthentication(roles);

    @Test
    void whenGetAuthorities_thenReturnRoles() {
        assertEquals(roles, cashFlowAuthentication.getAuthorities());
    }

    @Test
    void whenGetCredentials_thenReturnCashFlowCredentials() {
        CashFlowCredentials credentials = cashFlowAuthentication.getCredentials();
        assertAll(() -> {
            assertEquals(1L, credentials.id());
            assertEquals("Vinicius", credentials.firstName());
            assertEquals("Peralta", credentials.lastName());
            assertEquals("vinicius@email.com", credentials.email());
        });
    }

    @Test
    void whenGetDetails_thenReturnNull() {
     assertNull(cashFlowAuthentication.getDetails());
    }

    @Test
    void whenGetPrincipal_thenReturnNull() {
        assertNull(cashFlowAuthentication.getPrincipal());
    }

    @Test
    void whenIsAuthenticated_thenReturnTrue() {
        assertTrue(cashFlowAuthentication.isAuthenticated());
    }

    @Test
    void whenGetName_thenReturnFullName() {
        assertEquals("Vinicius Peralta", cashFlowAuthentication.getName());
    }

    @Test
    void whenGetJwtToken_thenReturnJwtToken() {
        assertEquals("jwtToken123", cashFlowAuthentication.getJwtToken());
    }

    @Test
    void whenSetAuthenticated_thenUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> cashFlowAuthentication.setAuthenticated(false));
    }

}