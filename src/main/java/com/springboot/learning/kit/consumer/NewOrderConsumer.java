package com.springboot.learning.kit.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Consumer class for handling order-related messages.
 */
@Slf4j
@Component
public class NewOrderConsumer {

    /**
     * Consumes messages from the ActiveMQ
     *
     * @param message the message received from the queue
     */
    public void processActiveMQOrder(String message) {
        log.error("Received new ActiveMQ order message: {}", message);
    }

    /**
     * Consumes messages from RabbitMQ
     *
     * @param message the message received from the queue
     */
    public void processRabbitMQOrder(String message) {


    }
}
