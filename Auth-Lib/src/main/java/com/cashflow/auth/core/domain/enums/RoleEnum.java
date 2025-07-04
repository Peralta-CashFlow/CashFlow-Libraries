package com.cashflow.auth.core.domain.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum class representing user roles in the CashFlow application implementing
 * SpringSecurity GrantedAuthority interface so it can be used for authorization.
 *
 * @see org.springframework.security.core.GrantedAuthority
 *
 * @author Vinicius Peralta
 * @since 1.0.0
 */
public enum RoleEnum implements GrantedAuthority {

    CASH_FLOW_BASICS("CASH_FLOW_BASICS");

    RoleEnum(String role) {
        this.role = role;
    }

    private final String role;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
