package com.cashflow.exception.core.domain.mapper;

import com.cashflow.exception.core.CashFlowException;
import com.cashflow.exception.core.domain.dto.response.ExceptionResponse;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Objects;

/**
 * ExceptionResponseMapper is a utility class that provides methods to convert different types of exceptions
 * into a standardized ExceptionResponse format.
 * <p>
 * This class is used to ensure that all exceptions thrown in the application are mapped to a consistent response structure,
 * which can be easily consumed by clients.
 * </p>
 *
 * @see #fromCashFlowException(CashFlowException)
 * @see #fromGenericException(Exception)
 *
 *
 * @author Vinicius Peralta
 * @since 1.0.0
 */
public class ExceptionResponseMapper {

    private ExceptionResponseMapper() {}

    /**
     * Converts a CashFlowException into an ExceptionResponse.
     *
     * @param cashFlowException the CashFlowException to convert
     * @return an ExceptionResponse containing the details of the CashFlowException
     *
     * @see CashFlowException
     * @see ExceptionResponse
     *
     * @since 1.0.0
     */
    public static ExceptionResponse fromCashFlowException(CashFlowException cashFlowException) {
        return new ExceptionResponse(
                cashFlowException.getTitle(),
                cashFlowException.getMessage(),
                cashFlowException.getClassName(),
                cashFlowException.getMethodName()
        );
    }

    /**
     * Converts a generic Exception into an ExceptionResponse.
     *
     * @param exception the Exception to convert
     * @return an ExceptionResponse containing the details of the Exception
     *
     * @see Exception
     * @see ExceptionResponse
     *
     * @since 1.0.0
     */
    public static ExceptionResponse fromGenericException(Exception exception) {
        return new ExceptionResponse(
                "Unexpected Error",
                exception.getMessage(),
                exception.getClass().getName(),
                exception.getStackTrace()[0].getMethodName()
        );
    }

    /**
     * Converts a MethodArgumentNotValidException into an ExceptionResponse.
     *
     * @param methodArgumentNotValidException the MethodArgumentNotValidException to convert
     * @return an ExceptionResponse containing the details of the MethodArgumentNotValidException
     *
     * @see MethodArgumentNotValidException
     * @see ExceptionResponse
     *
     * @since 1.0.0
     */
    public static ExceptionResponse fromMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException
    ) {
        String message = Objects.nonNull(methodArgumentNotValidException.getFieldError()) ?
                methodArgumentNotValidException.getFieldError().getDefaultMessage() :
                "Validation Error";
        return new ExceptionResponse(
                "Request Validation Error",
                message,
                methodArgumentNotValidException.getClass().getName(),
                methodArgumentNotValidException.getStackTrace()[0].getMethodName()
        );
    }

    /**
     * Converts an AuthorizationDeniedException into an ExceptionResponse.
     *
     * @param authorizationDeniedException the AuthorizationDeniedException to convert
     * @return an ExceptionResponse containing the details of the AuthorizationDeniedException
     *
     * @see AuthorizationDeniedException
     * @see ExceptionResponse
     *
     * @since 1.0.0
     */
    public static ExceptionResponse fromAuthorizationDeniedException(
            AuthorizationDeniedException authorizationDeniedException
    ) {
        return new ExceptionResponse(
                "Authorization Denied",
                "You are not authorized to perform this action.",
                authorizationDeniedException.getStackTrace()[0].getClassName(),
                authorizationDeniedException.getStackTrace()[0].getMethodName()
        );
    }

}
