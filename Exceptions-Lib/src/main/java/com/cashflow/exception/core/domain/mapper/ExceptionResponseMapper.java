package com.cashflow.exception.core.domain.mapper;

import com.cashflow.exception.core.CashFlowException;
import com.cashflow.exception.core.domain.dto.response.ExceptionResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Objects;

public class ExceptionResponseMapper {

    private ExceptionResponseMapper() {}

    public static ExceptionResponse fromCashFlowException(CashFlowException cashFlowException) {
        return new ExceptionResponse(
                cashFlowException.getTitle(),
                cashFlowException.getMessage(),
                cashFlowException.getClassName(),
                cashFlowException.getMethodName()
        );
    }

    public static ExceptionResponse fromGenericException(Exception exception) {
        return new ExceptionResponse(
                "Unexpected Error",
                exception.getMessage(),
                exception.getClass().getName(),
                exception.getStackTrace()[0].getMethodName()
        );
    }

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

}
