package com.cashflow.commons.core.dto.response;

import java.util.List;

/**
 *
 * Basic generic object to serve as a pagination response.
 *
 * @author Vinicius Peralta
 * @since 0.0.4
 */
public record PageResponse<T>(
        List<T> response,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
