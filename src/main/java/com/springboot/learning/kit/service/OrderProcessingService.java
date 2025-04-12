package com.springboot.learning.kit.service;

import com.springboot.learning.kit.domain.Order;
import com.springboot.learning.kit.processor.AbstractOrderProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProcessingService {

    private final Set<AbstractOrderProcessor> orderProcessors;
    private final OrderValidationService orderValidationService;

    public void processNewOrder(Order order) {

        // Perform validation on the order
        orderValidationService.validateOrder(order);

        orderProcessors.stream()
                .filter(processor -> processor.supports(order.getOrderType()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No processor found for order type: " + order.getOrderType()))
                .processOrder(order);
    }
}
