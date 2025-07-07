package com.cashflow.exception.core;

/**
 * CashFlowException is a custom exception class that extends the Exception class.
 * It is used to represent exceptions specific to the CashFlow application.
 * <p>
 * This class includes additional fields for HTTP status code, title, message, class name,
 * and method name to provide more context about the exception.
 * </p>
 *
 * @see Exception
 * @see #getHttpStatusCode()
 * @see #getTitle()
 * @see #getMessage()
 * @see #getClassName()
 * @see #getMethodName()
 *
 * @author Vinicius Peralta
 * @since 1.0.0
 */
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
