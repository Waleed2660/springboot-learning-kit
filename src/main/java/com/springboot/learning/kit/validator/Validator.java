package com.springboot.learning.kit.validator;

import com.springboot.learning.kit.exception.OrderValidationException;

public interface Validator<T> {
    /**
     * Validates the given object.
     *
     * @param object the object to validate
     * @throws OrderValidationException if validation fails
     */
    void validate(T object) throws OrderValidationException;
}
