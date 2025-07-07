package com.cashflow.exception.core.handler;

import com.cashflow.exception.core.CashFlowException;
import com.cashflow.exception.core.domain.dto.response.ExceptionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.security.authorization.AuthorizationDeniedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CashFlowExceptionHandlerTest {

    private CashFlowExceptionHandler cashFlowExceptionHandler = new CashFlowExceptionHandler();

    @Test
    void givenCashFlowException_whenHandleCashFlowException_thenReturnExceptionResponse() {
        CashFlowException cashFlowException = new CashFlowException(500, "Test Title", "Test Message", "TestClass", "testMethod");
        ExceptionResponse response = cashFlowExceptionHandler.handleCashFlowException(cashFlowException).getBody();
        assertNotNull(response);
        assertEquals("Test Title", response.getTitle());
        assertEquals("Test Message", response.getMessage());
        assertEquals("TestClass", response.getClassName());
        assertEquals("testMethod", response.getMethodName());
    }

    @Test
    void givenGenericException_whenFromGenericException_thenReturnExceptionResponse() {
        Exception exception = new Exception("Generic error");
        ExceptionResponse response = cashFlowExceptionHandler.handleGenericException(exception).getBody();
        assertNotNull(response);
        assertEquals("Generic error", response.getMessage());
        assertEquals("java.lang.Exception", response.getClassName());
        assertEquals("Unexpected Error", response.getTitle());
        assertEquals("givenGenericException_whenFromGenericException_thenReturnExceptionResponse", response.getMethodName());
    }

    @Test
    void givenAuthorizationDeniedException_whenHandleAuthorizationDeniedException_thenReturnExceptionResponse() {
        AuthorizationDeniedException exception = new AuthorizationDeniedException("Access denied");
        ExceptionResponse response = cashFlowExceptionHandler.handleAuthorizationDeniedException(exception).getBody();
        assertNotNull(response);
        assertEquals("You are not authorized to perform this action.", response.getMessage());
        assertEquals("com.cashflow.exception.core.handler.CashFlowExceptionHandlerTest", response.getClassName());
        assertEquals("Authorization Denied", response.getTitle());
        assertEquals("givenAuthorizationDeniedException_whenHandleAuthorizationDeniedException_thenReturnExceptionResponse", response.getMethodName());
    }
}