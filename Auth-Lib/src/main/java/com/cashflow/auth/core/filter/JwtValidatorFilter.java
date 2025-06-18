package com.cashflow.auth.core.filter;

import com.cashflow.auth.core.domain.mapper.AuthenticationMapper;
import com.cashflow.auth.core.service.jwt.CashFlowJwtService;
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

public class JwtValidatorFilter extends OncePerRequestFilter {

    private final CashFlowJwtService cashFlowJwtService;

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

    public JwtValidatorFilter(final CashFlowJwtService cashFlowJwtService,
                              final String[] notFilteredEndpoints) {
        this.cashFlowJwtService = cashFlowJwtService;
        this.notFilteredEndpoints = notFilteredEndpoints;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtToken = request.getHeader("Authorization");
            SecretKey secretKey = cashFlowJwtService.generateSecretKey();
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

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return Arrays.stream(notFilteredEndpoints).toList().contains(request.getServletPath()) ||
                swaggerConstants.stream().anyMatch(request.getServletPath()::contains) ||
                actuatorConstants.stream().anyMatch(request.getServletPath()::contains);
    }
}
