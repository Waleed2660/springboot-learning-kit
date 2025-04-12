package com.springboot.learning.kit.processor;

import com.springboot.learning.kit.domain.Order;
import com.springboot.learning.kit.domain.OrderType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractOrderProcessor {

    /**
     * Checks if this processor can handle the given order type.
     *
     * @param orderType the type of order
     */
    public boolean supports(OrderType orderType) {
        return orderType.equals(OrderType.ONLINE);
    }

    /**
     * Processes the order.
     *
     * @param order the order to process
     */
    public void processOrder(Order order) {
        log.error("Abstract Order Processor received order: {}", order);
    }
}
