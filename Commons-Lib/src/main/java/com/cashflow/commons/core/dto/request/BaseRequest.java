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

    private final Locale language;

    private final T request;

    private final long userId;

    public BaseRequest(Locale language, T request, long userId) {
        this.language = language;
        this.request = request;
        this.userId = userId;
    }

    public Locale getLanguage() {
        return language;
    }

    public T getRequest() {
        return request;
    }

    public long getUserId() { return userId; }
}
