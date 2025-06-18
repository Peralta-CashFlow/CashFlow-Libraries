package com.cashflow.auth.core.utils;

import com.cashflow.auth.core.domain.enums.RoleEnum;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthUtilsTest {

    @Test
    void givenRoleList_whenMapRoleListToString_thenReturnsCommaSeparatedString() {
        String result = AuthUtils.mapRoleListToString(List.of(RoleEnum.CASH_FLOW_BASICS, RoleEnum.CASH_FLOW_BASICS));
        assertEquals("CASH_FLOW_BASICS,CASH_FLOW_BASICS", result);
    }

    @Test
    void givenStringOfRoles_whenMapStringToRoleList_thenReturnsListOfRoles() {
        List<RoleEnum> result = AuthUtils.mapStringToRoleList("CASH_FLOW_BASICS,CASH_FLOW_BASICS");
        assertEquals(List.of(RoleEnum.CASH_FLOW_BASICS, RoleEnum.CASH_FLOW_BASICS), result);
    }

    @Test
    void givenApiWhiteList_whenWhiteListEndpoints_thenReturnsCombinedEndpointList() {
        String[] apiWhiteList = {"/auth/user/register", "/auth/user/login"};
        String[] result = AuthUtils.whiteListEndpoints(apiWhiteList);

        assertArrayEquals(
            new String[]{
                "/auth/user/register",
                "/auth/user/login",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/actuator/**"
            },
            result
        );
    }
}