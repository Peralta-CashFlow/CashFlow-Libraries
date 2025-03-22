package com.cashflow.auth.core.domain.mapper;

import com.cashflow.auth.core.domain.authentication.CashFlowAuthentication;
import com.cashflow.auth.core.utils.AuthUtils;
import io.jsonwebtoken.Claims;

public class AuthenticationMapper {

    private AuthenticationMapper() {}

    public static CashFlowAuthentication generateAuthenticationFromClaims(Claims claims, String jwtToken) {
        return new CashFlowAuthentication(
                claims.get("id", Long.class),
                claims.get("firstName", String.class),
                claims.get("lastName", String.class),
                claims.get("email", String.class),
                AuthUtils.mapStringToRoleList(claims.get("roles", String.class)),
                true,
                jwtToken
            );
    }
}
