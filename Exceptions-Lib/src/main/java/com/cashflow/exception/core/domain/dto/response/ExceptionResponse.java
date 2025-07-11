package com.cashflow.exception.core.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ExceptionResponse is a DTO class that represents the structure of an exception response
 * in the CashFlow application. It contains details about the exception such as title,
 * message, class name, and method name.
 * <p>
 * This class is used to provide a consistent format for error responses across the application.
 *
 * @author Vinicius Peralta
 * @since 1.0.0
 */
@Schema(description = "CashFlow's Exception base response.")
public class ExceptionResponse {

    @Schema(description = "Exception title", example = "Invalid request data")
    private final String title;

    @Schema(description = "Exception message", example = "The provided request data is invalid.")
    private final String message;

    @Schema(description = "Exception class name", example = "UserController.java")
    private final String className;

    @Schema(description = "Exception method name", example = "register")
    private final String methodName;

    public ExceptionResponse(String title, String message, String className, String methodName) {
        this.title = title;
        this.message = message;
        this.className = className;
        this.methodName = methodName;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }


}
