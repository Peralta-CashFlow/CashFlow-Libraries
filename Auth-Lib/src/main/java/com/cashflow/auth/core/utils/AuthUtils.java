package com.cashflow.auth.core.utils;

import com.cashflow.auth.core.domain.enums.RoleEnum;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class for authentication-related operations.
 *
 * @see #mapStringToRoleList(String)
 * @see #mapRoleListToString(List)
 * @see #getJwtSecretKey()
 *
 * @author Vinicius Peralta
 * @since 1.0.0
 */
public class AuthUtils {

    private AuthUtils() {
    }

    /**
     * Converts a list of RoleEnum to a comma-separated string.
     *
     * @param roles List of RoleEnum
     * @return Comma-separated string of role names
     *
     * @see RoleEnum
     *
     * @since 1.0.0
     *
     */
    public static String mapRoleListToString(List<RoleEnum> roles) {
        return roles.stream()
                .map(RoleEnum::name)
                .collect(Collectors.joining(","));
    }

    /**
     * Converts a comma-separated string of role names to a list of RoleEnum.
     *
     * @param roles Comma-separated string of role names
     * @return List of RoleEnum
     *
     * @see RoleEnum
     *
     * @since 1.0.0
     */
    public static List<RoleEnum> mapStringToRoleList(String roles) {
        return Stream.of(roles.split(","))
                .map(RoleEnum::valueOf)
                .toList();
    }

    /**
     *
     * Retrieves the JWT secret key from the environment variable "JWT_SECRET".
     *
     * @return SecretKey for signing JWT tokens
     *
     * @see SecretKey
     *
     * @since 1.0.0
     */
    public static SecretKey getJwtSecretKey() {
        String jwtSecret = System.getenv("JWT_SECRET");
        if (jwtSecret == null || jwtSecret.isEmpty()) {
            throw new IllegalStateException("JWT_SECRET environment variable is not set");
        }
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}
