package com.cashflow.auth.core.utils;

import com.cashflow.auth.core.domain.enums.RoleEnum;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SystemStubsExtension.class)
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
    void shouldReturnSecretKeyWhenEnvVariableIsSet(EnvironmentVariables environmentVariables) {
        String secret = "12345678901234567890123456789012";
        environmentVariables.set("JWT_SECRET", secret);
        SecretKey key = AuthUtils.getJwtSecretKey();
        assertNotNull(key);
        assertEquals(
                Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)).getEncoded().length,
                key.getEncoded().length
        );
    }

    @Test
    void shouldThrowExceptionWhenEnvVariableIsNotSet(EnvironmentVariables environmentVariables) {
        environmentVariables.set("JWT_SECRET", "");
        IllegalStateException exception = assertThrows(IllegalStateException.class, AuthUtils::getJwtSecretKey);
        assertEquals("JWT_SECRET environment variable is not set", exception.getMessage());
    }
}