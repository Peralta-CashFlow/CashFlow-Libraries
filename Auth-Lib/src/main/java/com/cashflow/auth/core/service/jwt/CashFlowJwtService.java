package com.cashflow.auth.core.service.jwt;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class CashFlowJwtService {

    private final String jwtSecret;

    public CashFlowJwtService(final String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public SecretKey generateSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

}
