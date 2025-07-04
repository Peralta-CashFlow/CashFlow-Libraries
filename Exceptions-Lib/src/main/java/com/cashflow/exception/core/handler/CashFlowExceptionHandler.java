package com.cashflow.exception.core.handler;

import com.cashflow.exception.core.CashFlowException;
import com.cashflow.exception.core.domain.dto.response.ExceptionResponse;
import com.cashflow.exception.core.domain.mapper.ExceptionResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * CashFlowExceptionHandler is a global exception handler for the CashFlow applicationS.
 * It intercepts exceptions thrown by controllers and maps them to appropriate ExceptionResponse responses.
 * <p>
 * This class handles specific exceptions like CashFlowException, MethodArgumentNotValidException,
 * and generic exceptions, providing a consistent response structure for clients.
 * </p>
 *
 * @see #handleCashFlowException(CashFlowException)
 * @see #handleGenericException(Exception)
 * @see #handleMethodArgumentNotValidException(MethodArgumentNotValidException)
 * @see #handleAuthorizationDeniedException(AuthorizationDeniedException) 
 *
 * @author Vinicius Peralta
 * @since 1.0.0
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class CashFlowExceptionHandler {

    /**
     * Handles CashFlowException and maps it to an ExceptionResponse.
     *
     * @param cashFlowException the CashFlowException to handle
     * @return a ResponseEntity containing the ExceptionResponse with the appropriate HTTP status code
     *
     * @see CashFlowException
     * @see ExceptionResponse
     * @see ExceptionResponseMapper
     *
     * @since 1.0.0
     */
    @ExceptionHandler(CashFlowException.class)
    public ResponseEntity<ExceptionResponse> handleCashFlowException(CashFlowException cashFlowException) {
        return ResponseEntity
                .status(cashFlowException.getHttpStatusCode())
                .body(ExceptionResponseMapper.fromCashFlowException(cashFlowException));
    }

    /**
     * Handles generic exceptions and maps them to an ExceptionResponse.
     *
     * @param exception the Exception to handle
     * @return a ResponseEntity containing the ExceptionResponse with a 500 Internal Server Error status code
     *
     * @see Exception
     * @see ExceptionResponse
     * @see ExceptionResponseMapper
     *
     * @since 1.0.0
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception exception) {
        return ResponseEntity
                .status(500)
                .body(ExceptionResponseMapper.fromGenericException(exception));
    }

    /**
     * Handles MethodArgumentNotValidException and maps it to an ExceptionResponse.
     *
     * @param methodArgumentNotValidException the MethodArgumentNotValidException to handle
     * @return a ResponseEntity containing the ExceptionResponse with a 400 Bad Request status code
     *
     * @see MethodArgumentNotValidException
     * @see ExceptionResponse
     * @see ExceptionResponseMapper
     *
     * @since 1.0.0
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException
    ) {
        return ResponseEntity
                .status(400)
                .body(ExceptionResponseMapper.fromMethodArgumentNotValidException(methodArgumentNotValidException));
    }

    /**
     * Handles AuthorizationDeniedException and maps it to an ExceptionResponse.
     *
     * @param authorizationDeniedException the AuthorizationDeniedException to handle
     * @return a ResponseEntity containing the ExceptionResponse with a 403 Forbidden status code
     *
     * @see AuthorizationDeniedException
     * @see ExceptionResponse
     * @see ExceptionResponseMapper
     *
     * @since 1.0.0
     */
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAuthorizationDeniedException(
            AuthorizationDeniedException authorizationDeniedException
    ) {
        return ResponseEntity
                .status(403)
                .body(ExceptionResponseMapper.fromAuthorizationDeniedException(authorizationDeniedException));
    }

}
