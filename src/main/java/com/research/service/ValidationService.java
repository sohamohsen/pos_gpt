package com.research.service;

import com.research.exception.BusinessRuleViolationException;

public class ValidationService {

    public void notNull(Object obj, String message) {
        if (obj == null) {
            throw new BusinessRuleViolationException(message);
        }
    }

    public void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new BusinessRuleViolationException(message);
        }
    }

    public void positive(int value, String fieldName) {
        if (value <= 0) {
            throw new BusinessRuleViolationException(fieldName + " must be positive");
        }
    }

    public void positive(double value, String fieldName) {
        if (value <= 0) {
            throw new BusinessRuleViolationException(fieldName + " must be positive");
        }
    }
}
