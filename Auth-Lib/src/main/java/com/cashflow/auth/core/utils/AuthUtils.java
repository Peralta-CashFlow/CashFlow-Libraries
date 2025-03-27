package com.cashflow.auth.core.utils;

import com.cashflow.auth.core.domain.enums.RoleEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AuthUtils {

    private AuthUtils() {
    }

    public static String mapRoleListToString(List<RoleEnum> roles) {
        return roles.stream()
                .map(RoleEnum::name)
                .collect(Collectors.joining(","));
    }

    public static List<RoleEnum> mapStringToRoleList(String roles) {
        return Stream.of(roles.split(","))
                .map(RoleEnum::valueOf)
                .toList();
    }

    public static String[] whiteListEndpoints(String[] apiWhiteList) {
        List<String> whiteListEndpoints = new ArrayList<>(Arrays.asList(apiWhiteList));
        whiteListEndpoints.addAll(BASE_WHITELIST_ENDPOINTS);
        return whiteListEndpoints.toArray(new String[0]);
    }

    private static final List<String> BASE_WHITELIST_ENDPOINTS = List.of(
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    );

}
