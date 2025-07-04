package com.cashflow.auth.core.filter;

import com.cashflow.auth.core.domain.mapper.AuthenticationMapper;
import com.cashflow.auth.core.utils.AuthUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * This filter validates if the JWT token is valid by parsing it and verifying its signature, it extends
 * OncePerRequestFilter interface to ensure it is executed once per request.
 *
 * @see OncePerRequestFilter
 * @see #doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)
 * @see #shouldNotFilter(HttpServletRequest) 
 */
public class JwtValidatorFilter extends OncePerRequestFilter {

    private final String[] notFilteredEndpoints;

    private final List<String> swaggerConstants = List.of(
            "api-docs",
            "/swagger"
    );

    private final List<String> actuatorConstants = List.of(
            "actuator",
            "/health",
            "/info"
    );

    /**
     * @param notFilteredEndpoints Array of endpoints that should not be filtered
     */
    public JwtValidatorFilter(final String[] notFilteredEndpoints) {
        this.notFilteredEndpoints = notFilteredEndpoints;
    }

    /**
     * Validates the JWT token from the Authorization header.
     * <p>
     * If valid, sets the authentication in the security context.
     * If invalid, responds with 401 Unauthorized.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param filterChain FilterChain
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException If an I/O error occurs
     *
     * @since 1.0.0
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtToken = request.getHeader("Authorization");
            SecretKey secretKey = AuthUtils.getJwtSecretKey();
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(jwtToken)
                    .getPayload();
            SecurityContextHolder.getContext().setAuthentication(AuthenticationMapper.generateAuthenticationFromClaims(claims, jwtToken));
        } catch (Exception exception) {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().write("JWT Token invalid");
            return;
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Determines if the filter should not be applied to the given request.
     * <p>
     * The filter is skipped for endpoints specified in the notFilteredEndpoints array,
     * as well as for Swagger and Actuator related endpoints.
     *
     * @param request HttpServletRequest
     * @return true if the filter should not be applied, false otherwise
     *
     * @since 1.0.0
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return Arrays.stream(notFilteredEndpoints).toList().contains(request.getServletPath()) ||
                swaggerConstants.stream().anyMatch(request.getServletPath()::contains) ||
                actuatorConstants.stream().anyMatch(request.getServletPath()::contains);
    }
}
