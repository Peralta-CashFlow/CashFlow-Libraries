package com.cashflow.exception.core.handler;

import com.cashflow.exception.core.CashFlowException;
import com.cashflow.exception.core.domain.dto.response.ExceptionResponse;
import com.cashflow.exception.core.domain.mapper.ExceptionResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class CashFlowExceptionHandler {

    @ExceptionHandler(CashFlowException.class)
    public ResponseEntity<ExceptionResponse> handleCashFlowException(CashFlowException cashFlowException) {
        return ResponseEntity
                .status(cashFlowException.getHttpStatusCode())
                .body(ExceptionResponseMapper.fromCashFlowException(cashFlowException));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception exception) {
        return ResponseEntity
                .status(500)
                .body(ExceptionResponseMapper.fromGenericException(exception));
    }

}
