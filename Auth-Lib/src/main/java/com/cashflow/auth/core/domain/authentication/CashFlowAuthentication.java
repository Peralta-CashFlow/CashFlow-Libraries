package com.cashflow.auth.core.domain.authentication;

import com.cashflow.auth.core.domain.enums.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Schema(description = "CashFlow authentication information")
public class CashFlowAuthentication implements Authentication {

    @Schema(description = "User ID", example = "1")
    private final Long id;

    @Schema(description = "User first name", example = "Vinicius")
    private final String firstName;

    @Schema(description = "User last name", example = "Peralta")
    private final String lastName;

    @Schema(description = "User e-mail", example = "vinicius-peralta@hotmail.com")
    private final String email;

    @Schema(description = "User roles", example = "[\"CASH_FLOW_BASICS\"]")
    private final List<RoleEnum> roles;

    @Schema(description = "User authentication status", example = "true")
    private final Boolean authenticated;

    @Schema(description = "JWT token", example = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJDYXNoRmxvdyIsInN1YiI6IjQiLCJpYXQiOjE3NDI2NjYyNTEsImV4cCI6MTc0MjY3NzA1MSwiaWQiOjQsImZpcnN0TmFtZSI6IlZpbmljaXVzIiwibGFzdE5hbWUiOiJQZXJhbHRhIiwiZW1haWwiOiJ2aW5pY2l1cy1wZXJhbHRhQGhvdG1haWwuY29tIiwicm9sZXMiOiJDQVNIX0ZMT1dfQkFTSUNTIn0.AVRjCp_feXr0qCx06AFxL4S5BkfK45qvNwiYGe3EfVMjKbYgKaKn9IaphUKv4kkpCBNGEXuZZrCRi3s7H1aizg")
    private final String jwtToken;

    public CashFlowAuthentication(final Long id,
                                  final String firstName,
                                  final String lastName,
                                  final String email,
                                  final List<RoleEnum> roles,
                                  final Boolean authenticated,
                                  final String jwtToken) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.authenticated = authenticated;
        this.jwtToken = jwtToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public CashFlowCredentials getCredentials() {
        return new CashFlowCredentials(
                this.id,
                this.firstName,
                this.lastName,
                this.email
        );
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        throw new UnsupportedOperationException("Setting authentication is not supported.");
    }

    @Override
    public String getName() {
        return this.firstName.concat(" ").concat(this.lastName);
    }

    public String getJwtToken() {
        return this.jwtToken;
    }
}
