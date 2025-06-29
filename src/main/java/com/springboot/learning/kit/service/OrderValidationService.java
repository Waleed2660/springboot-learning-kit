package com.springboot.learning.kit.service;

import com.springboot.learning.kit.dto.request.OrderRequest;
import com.springboot.learning.kit.exception.OrderValidationException;
import com.springboot.learning.kit.validator.CustomerDetailsValidator;
import com.springboot.learning.kit.validator.OrderTypeValidator;
import com.springboot.learning.kit.validator.OrderUUIDValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderValidationService {

    private final OrderUUIDValidator orderUUIDValidator;
    private final OrderTypeValidator orderTypeValidator;
    private final CustomerDetailsValidator customerDetailsValidator;

    /**
     * Validates the given order.
     *
     * @param orderRequest the order to be validated
     * @throws OrderValidationException if the order is invalid
     */
    public void validateOrder(OrderRequest orderRequest) {
        orderUUIDValidator.validate(orderRequest.getUUID());
        orderTypeValidator.validate(orderRequest.getOrderType());
        customerDetailsValidator.validate(orderRequest.getCustomerDetails());
    }
}
