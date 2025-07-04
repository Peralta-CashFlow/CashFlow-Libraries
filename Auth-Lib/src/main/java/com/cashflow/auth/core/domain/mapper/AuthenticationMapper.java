package com.cashflow.auth.core.domain.mapper;

import com.cashflow.auth.core.domain.authentication.CashFlowAuthentication;
import com.cashflow.auth.core.utils.AuthUtils;
import io.jsonwebtoken.Claims;

/**
 * Mapper class for converting JWT claims to CashFlowAuthentication objects.
 *
 * @see CashFlowAuthentication
 * @see #generateAuthenticationFromClaims(Claims, String)
 *
 * @author Vinicius Peralta
 * @since 1.0.0
 */
public class AuthenticationMapper {

    private AuthenticationMapper() {}

    /**
     * Generates a CashFlowAuthentication object from JWT claims and the original JWT token.
     *
     * @param claims The JWT claims containing user information
     * @param jwtToken The JWT token
     * @return a CashFlowAuthentication object populated with user details from the claims
     * @see CashFlowAuthentication
     * @see AuthUtils
     *
     * @since 1.0.0
     */
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
