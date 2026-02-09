package com.cashflow.auth.core.templates;

import com.cashflow.auth.core.domain.authentication.CashFlowAuthentication;
import com.cashflow.auth.core.domain.enums.RoleEnum;

import java.util.List;

public class CashFlowAuthenticationTemplates {

    private CashFlowAuthenticationTemplates() {
    }

    public static CashFlowAuthentication cashFlowAuthentication(List<RoleEnum> roles) {
        return new CashFlowAuthentication(
                1L,
                "Vinicius",
                "Peralta",
                "vinicius@email.com",
                roles,
                true,
                "jwtToken123"
        );
    }
}
