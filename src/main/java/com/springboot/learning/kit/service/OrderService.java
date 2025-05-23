package com.springboot.learning.kit.service;

import com.springboot.learning.kit.domain.Order;
import com.springboot.learning.kit.event.OrderPlacedEvent;
import com.springboot.learning.kit.dto.request.OrderRequest;
import com.springboot.learning.kit.exception.DuplicateOrderException;
import com.springboot.learning.kit.producer.OrderEventProducer;
import com.springboot.learning.kit.transformer.OrderEventTransformer;
import com.springboot.learning.kit.transformer.OrderTransformer;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderTransformer orderTransformer;
    private final EntityManager entityManager;
    private final OrderEventProducer orderEventProducer;
    private final OrderEventTransformer orderEventTransformer;

    public void saveNewOrder(OrderRequest orderRequest, long customerId, long addressId) {
        log.info("Saving new order: {}", orderRequest);

        Order order = orderTransformer.transformOrderRequestToDomain(orderRequest, customerId, addressId);

        try {
            entityManager.persist(order);
            entityManager.flush();
        } catch (ConstraintViolationException e) {
            log.error("Error saving order: ", e);
            throw new DuplicateOrderException("Order with UUID " + orderRequest.getUUID() + " already exists.");
        }
    }

    public void publishOrderPlacedEvent(OrderRequest orderRequest) {
        log.info("Publishing order placed event for order: {}", orderRequest.getUUID());

        OrderPlacedEvent orderPlacedEvent = orderEventTransformer.transformToOrderPlacedEvent(orderRequest);

        orderEventProducer.sendNewOrderNotificationToVirtualTopic(orderPlacedEvent);

        orderEventProducer.sendNewOrderNotificationToRabbitMQQueue(orderPlacedEvent);
    }
}
