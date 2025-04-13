package com.springboot.learning.kit.validator;

import com.springboot.learning.kit.domain.OrderType;
import com.springboot.learning.kit.exception.OrderValidationException;
import org.springframework.stereotype.Component;

@Component
public class OrderTypeValidator implements Validator<String> {

    @Override
    public void validate(String orderType) throws OrderValidationException {
        if (orderType == null || orderType.isEmpty()) {
            throw new OrderValidationException("Order type cannot be null");
        }

        if (!OrderType.getAllOrderTypes().contains(OrderType.valueOf(orderType))) {
            throw new OrderValidationException("Invalid order type: " + orderType);
        }
    }
}
