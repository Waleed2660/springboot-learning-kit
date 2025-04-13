package com.springboot.learning.kit.service;

import com.springboot.learning.kit.dto.request.OrderRequest;
import com.springboot.learning.kit.exception.OrderValidationException;
import com.springboot.learning.kit.validator.OrderTypeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderValidationService {

    private final OrderTypeValidator orderTypeValidator;

    /**
     * Validates the given order.
     *
     * @param orderRequest the order to be validated
     * @throws OrderValidationException if the order is invalid
     */
    public void validateOrder(OrderRequest orderRequest) {
        orderTypeValidator.validate(orderRequest.getOrderType());
    }
}
