package com.cashflow.commons.core.dto.request;

import java.util.Locale;

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
