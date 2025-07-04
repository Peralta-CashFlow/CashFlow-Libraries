package com.cashflow.auth.core.domain.authentication;

/**
 * CashFlowCredentials record to hold user credentials information.
 *
 * @param id
 * @param firstName
 * @param lastName
 * @param email
 *
 * @author Vinicius Peralta
 * @since 1.0.0
 */
public record CashFlowCredentials(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
