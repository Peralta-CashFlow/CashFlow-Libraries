package com.cashflow.auth.core.domain.mapper;

import com.cashflow.auth.core.domain.authentication.CashFlowAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationMapperTest {

    @Test
    void givenClaimsAndJwtToken_whenGenerateAuthenticationFromClaims_thenReturnCashFlowAuthentication() {
        String jwtToken = "testJwtToken";
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@mail.com";
        String roles = "CASH_FLOW_BASICS";
        Claims claims = Jwts.claims().add(
                Map.of(
                        "id", id,
                        "firstName", firstName,
                        "lastName", lastName,
                        "email", email,
                        "roles", roles
                )
        ).build();
        CashFlowAuthentication cashFlowAuthentication = AuthenticationMapper.generateAuthenticationFromClaims(claims, jwtToken);
        assertAll(() -> {
            assertTrue(cashFlowAuthentication.isAuthenticated());
            assertEquals(jwtToken, cashFlowAuthentication.getJwtToken());
            assertTrue(cashFlowAuthentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(roles)));
        });
    }

}