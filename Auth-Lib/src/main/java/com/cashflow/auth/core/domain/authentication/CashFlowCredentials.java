package com.cashflow.auth.core.domain.authentication;

public record CashFlowCredentials(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
