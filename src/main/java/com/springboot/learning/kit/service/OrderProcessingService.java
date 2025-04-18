package com.springboot.learning.kit.service;

import com.springboot.learning.kit.domain.OrderType;
import com.springboot.learning.kit.dto.request.OrderRequest;
import com.springboot.learning.kit.processor.AbstractOrderProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProcessingService {

    private final Set<AbstractOrderProcessor> orderProcessors;
    private final OrderValidationService orderValidationService;

    @Transactional
    public void processNewOrder(OrderRequest orderRequest) {

        // Perform validation on the incoming order request
        orderValidationService.validateOrder(orderRequest);

        orderProcessors.stream()
                .filter(processor -> processor.supports(OrderType.valueOf(orderRequest.getOrderType())))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No processor found for order type: " + orderRequest.getOrderType()))
                .processOrder(orderRequest);
    }
}
