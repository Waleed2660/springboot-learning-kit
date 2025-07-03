package com.springboot.learning.kit.validator;

import com.springboot.learning.kit.exception.OrderValidationException;
import org.springframework.stereotype.Component;

@Component
public class OrderUUIDValidator implements Validator<Long> {

    @Override
    public void validate(Long uuid) throws OrderValidationException {
        System.out.println("Validating UUID: " + uuid);
        if (uuid == null) {
            throw new OrderValidationException("UUID cannot be null");
        }
        if (uuid <= 0) {
            throw new OrderValidationException("UUID must be positive");
        }
    }
}
