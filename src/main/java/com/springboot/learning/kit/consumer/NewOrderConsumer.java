package com.springboot.learning.kit.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.learning.kit.dto.request.OrderRequest;
import com.springboot.learning.kit.exception.OrderProcessingException;
import com.springboot.learning.kit.service.OrderProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Consumer class for handling order-related messages.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NewOrderConsumer {

    private final ObjectMapper objectMapper;
    private final OrderProcessingService orderProcessingService;

    /**
     * Consumes messages from the ActiveMQ
     *
     * @param message the message received from the queue
     */
    public void processActiveMQOrder(String message) {
        try {
            log.error("Received new ActiveMQ order message");
            OrderRequest orderRequest = toOrderRequest(message);
            orderProcessingService.processNewOrder(orderRequest);
        } catch (Exception e) {
            log.error("Failed to process ActiveMQ order message: {}", message, e);
            throw new OrderProcessingException("Failed to process order message: " + message, e);
        }
    }

    /**
     * Consumes messages from RabbitMQ
     *
     * @param message the message received from the queue
     */
    @RabbitListener(queues = "${rmq.order.placement.queue}")
    public void processRabbitMQOrder(String message) {
        try {
            log.error("Received new RabbitMQ order message");
            OrderRequest orderRequest = toOrderRequest(message);
            orderProcessingService.processNewOrder(orderRequest);
        } catch (Exception e) {
            log.error("Failed to process RabbitMQ order message: {}", message, e);
            throw new AmqpRejectAndDontRequeueException("Non-retryable error", e);
        }
    }

    private OrderRequest toOrderRequest(String message) {
        try {
            return objectMapper.readValue(message, OrderRequest.class);
        } catch (JsonProcessingException e) {
            log.error("Failed to parse order message: {}", message, e);
            throw new OrderProcessingException("Invalid order received ~ " + e);
        }
    }
}
