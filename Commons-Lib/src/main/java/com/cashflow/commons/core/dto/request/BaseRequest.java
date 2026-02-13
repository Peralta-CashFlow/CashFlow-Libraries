package com.cashflow.commons.core.dto.request;

import java.util.Locale;

/**
 *
 * Basic generic object to maintain request basic attributes
 *
 * @author Vinicius Peralta
 * @since 0.0.1
 *
 */
public class BaseRequest<T> {

    private final String jwtToken;

    private final Locale language;

    private final T request;

    public BaseRequest(String jwtToken, Locale language, T request) {
        this.jwtToken = jwtToken;
        this.language = language;
        this.request = request;
    }

    public BaseRequest(Locale language, T request) {
        this.jwtToken = null;
        this.language = language;
        this.request = request;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public Locale getLanguage() {
        return language;
    }

    public T getRequest() {
        return request;
    }
    
}
