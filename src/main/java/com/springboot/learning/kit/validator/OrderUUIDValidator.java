package com.springboot.learning.kit.validator;


import com.springboot.learning.kit.exception.OrderValidationException;
import org.springframework.stereotype.Component;

@Component
public class OrderUUIDValidator implements Validator<Long>{


    @Override
    public void validate(Long object) throws OrderValidationException {
        if (object == null) {
            throw new OrderValidationException("Valid Order UUID is required");
        }
        if (object.intValue() < 0) {
            throw new OrderValidationException("Valid Order UUID is required");
        }
    }
}
