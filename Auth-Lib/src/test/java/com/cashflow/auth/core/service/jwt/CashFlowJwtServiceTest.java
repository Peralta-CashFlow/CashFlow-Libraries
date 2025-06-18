package com.cashflow.auth.core.service.jwt;

import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.*;

class CashFlowJwtServiceTest {

    @Test
    void testGenerateSecretKeyReturnsValidSecretKey() {
        String secret = "jwtSecretKey-for-testing-1234567890";
        CashFlowJwtService service = new CashFlowJwtService(secret);

        SecretKey key = service.generateSecretKey();

        assertNotNull(key);
        assertEquals("HmacSHA256", key.getAlgorithm());
    }
}