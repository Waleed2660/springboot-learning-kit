package com.springboot.learning.kit.validator;

import com.springboot.learning.kit.domain.OrderType;
import com.springboot.learning.kit.exception.OrderValidationException;
import org.springframework.stereotype.Component;

@Component
public class OrderTypeValidator implements Validator<OrderType> {

    @Override
    public void validate(OrderType orderType) throws OrderValidationException {
        if (orderType == null) {
            throw new OrderValidationException("Order type cannot be null");
        }

        if (OrderType.getAllOrderTypes().contains(orderType)) {
            throw new OrderValidationException("Invalid order type: " + orderType);
        }
    }
}
