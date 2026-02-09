package com.cashflow.auth.core.utils;

import com.cashflow.auth.core.domain.authentication.CashFlowAuthentication;
import com.cashflow.auth.core.domain.enums.RoleEnum;
import com.cashflow.auth.core.templates.CashFlowAuthenticationTemplates;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SystemStubsExtension.class)
class AuthUtilsTest {

    @AfterEach
    void clearContext() {
        SecurityContextHolder.clearContext();
    }

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

    @Test
    void shouldReturnIdWhenSecurityContextDefined() {

        CashFlowAuthentication cashFlowAuthentication = CashFlowAuthenticationTemplates.cashFlowAuthentication(List.of(RoleEnum.CASH_FLOW_BASICS));

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(cashFlowAuthentication);

        SecurityContextHolder.setContext(securityContext);

        assertEquals(cashFlowAuthentication.getCredentials().id(), AuthUtils.getUserIdFromSecurityContext());
    }

    @Test
    void shouldReturnExceptionWhenSecurityContextNotDefined() {

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(null);

        assertThrows(NullPointerException.class, AuthUtils::getUserIdFromSecurityContext);

    }
}