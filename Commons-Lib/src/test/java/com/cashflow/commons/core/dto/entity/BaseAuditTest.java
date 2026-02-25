package com.cashflow.commons.core.dto.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseAuditTest {

    @Test
    void testCreateAudit() {
        BaseAudit audit = new BaseAudit() {};
        long userId = 123L;
        audit.createAudit(userId);

        assertEquals(userId, audit.getCreatedBy());
        assertNotNull(audit.getCreatedAt());
    }

    @Test
    void testUpdateAudit() {
        BaseAudit audit = new BaseAudit() {};
        long userId = 123L;
        audit.updateAudit(userId);

        assertEquals(userId, audit.getUpdatedBy());
        assertNotNull(audit.getUpdatedAt());
    }

}