package com.cashflow.auth.core.utils;

import com.cashflow.auth.core.domain.enums.RoleEnum;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AuthUtils {

    private AuthUtils() {}

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

}
