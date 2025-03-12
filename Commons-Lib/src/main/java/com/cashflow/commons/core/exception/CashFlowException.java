package com.cashflow.commons.core.exception;

public class CashFlowException extends Exception {
    private final Integer httpStatusCode;
    private final String title;
    private final String message;
    private final String className;
    private final String methodName;

    public CashFlowException(Integer httpStatusCode, String title, String message, String className, String methodName) {
        super(message);
        this.httpStatusCode = httpStatusCode;
        this.title = title;
        this.message = message;
        this.className = className;
        this.methodName = methodName;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getTitle() {
        return title;
    }

    @Override
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
