package com.cashflow.exception.core.domain.dto.response;

public class ExceptionResponse {

    private final String title;

    private final String message;

    private final String className;

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
