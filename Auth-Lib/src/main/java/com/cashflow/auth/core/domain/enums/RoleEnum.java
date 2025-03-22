package com.cashflow.auth.core.domain.enums;

import org.springframework.security.core.GrantedAuthority;

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
