package com.cashflow.auth.core.domain.enums;

public enum RoleEnum {

    CASH_FLOW_BASICS("cash-flow-basics");

    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
