package com.cashflow.exception.core.domain.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ExceptionResponseTest {

    private final ExceptionResponse exceptionResponse = new ExceptionResponse(
            "Invalid request data",
            "The provided request data is invalid.",
            "UserController.java",
            "register"
    );

    @Test
    void getTitle() {
        assertEquals("Invalid request data", exceptionResponse.getTitle());
    }

    @Test
    void getMessage() {
        assertEquals("The provided request data is invalid.", exceptionResponse.getMessage());
    }

    @Test
    void getClassName() {
        assertEquals("UserController.java", exceptionResponse.getClassName());
    }

    @Test
    void getMethodName() {
        assertEquals("register", exceptionResponse.getMethodName());
    }
}