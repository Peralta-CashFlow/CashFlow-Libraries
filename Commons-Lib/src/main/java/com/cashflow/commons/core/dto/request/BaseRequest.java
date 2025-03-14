package com.cashflow.commons.core.dto.request;

public class BaseRequest<T> {

    private final String jwtToken;

    private final String language;

    private final T request;

    public BaseRequest(String jwtToken, String language, T request) {
        this.jwtToken = jwtToken;
        this.language = language;
        this.request = request;
    }

    public BaseRequest(String language, T request) {
        this.jwtToken = null;
        this.language = language;
        this.request = request;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public String getLanguage() {
        return language;
    }

    public T getRequest() {
        return request;
    }
    
}
