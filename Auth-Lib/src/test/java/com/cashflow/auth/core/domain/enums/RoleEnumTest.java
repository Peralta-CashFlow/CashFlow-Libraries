package com.cashflow.auth.core.domain.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleEnumTest {

    @Test
    void whenGetAuthority_thenReturnRole() {
        RoleEnum role = RoleEnum.CASH_FLOW_BASICS;
        String expectedRole = "CASH_FLOW_BASICS";

        assertEquals(expectedRole, role.getAuthority());
    }

}