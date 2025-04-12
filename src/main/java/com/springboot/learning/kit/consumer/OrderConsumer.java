package com.springboot.learning.kit.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Consumer class for handling order-related messages.
 */
@Slf4j
@Component
public class OrderConsumer {

    /**
     * Consumes messages from the "order.placement.queue".
     *
     * @param message the message received from the queue
     */
    @JmsListener(destination = "order.placement.queue")
    public void consumeNewOrder(String message) {
        log.error("Received new order message: {}", message);
    }

    /**
     * Consumes messages from the "order.cancellation.queue".
     *
     * @param message the message received from the queue
     */
    @JmsListener(destination = "order.cancellation.queue")
    public void consumeOrderCancellation(String message) {


    }
}
