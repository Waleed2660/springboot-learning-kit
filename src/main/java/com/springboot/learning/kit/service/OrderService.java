package com.springboot.learning.kit.service;

import com.springboot.learning.kit.domain.Order;
import com.springboot.learning.kit.dto.request.OrderRequest;
import com.springboot.learning.kit.repository.OrderRepository;
import com.springboot.learning.kit.transformer.OrderTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderTransformer orderTransformer;
    private final OrderRepository orderRepository;

    public void saveNewOrder(OrderRequest orderRequest, long customerId, long addressId) {
        log.info("Saving new order: {}", orderRequest);

        Order order = orderTransformer.transformOrderRequestToDomain(orderRequest, customerId, addressId);

        orderRepository.save(order);
    }
}
