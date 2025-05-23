package com.springboot.learning.kit.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.learning.kit.event.OrderPlacedEvent;
import com.springboot.learning.kit.exception.OrderEventProducerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventProducer {

    @Value("${amq.new.order.placed.topic}")
    private String virtualTopic;

    @Value("${rmq.new.order.notification.routing.key}")
    private String rabbitMQOrderNotificationRoutingKey;

    @Value("${rmq.topic.exchange}")
    private String topicExchange;

    private final ObjectMapper objectMapper;
    private final ProducerTemplate producerTemplate;
    private final RabbitTemplate rabbitTemplate;

    public void sendNewOrderNotificationToVirtualTopic(OrderPlacedEvent orderPlacedEvent) {
        try {
            // Convert the OrderPlacedEvent to JSON string
            String message = objectMapper.writeValueAsString(orderPlacedEvent);

            // Send the message to the virtual topic
            String destination = "activemq:topic:" + virtualTopic;
            producerTemplate.sendBody(destination, message);

        } catch (Exception e) {
            log.error("Failed to send new order notification to virtual topic", e);
            throw new OrderEventProducerException("Failed to send new order notification to virtual " +
                    "topic for order: " + orderPlacedEvent.getOrderId(), e);
        }
    }

    public void sendNewOrderNotificationToRabbitMQQueue(OrderPlacedEvent orderPlacedEvent) {
        try {
            // Convert the OrderPlacedEvent to JSON string
            String message = objectMapper.writeValueAsString(orderPlacedEvent);

            // Send the message to the topic exchange
            rabbitTemplate.convertAndSend(topicExchange, rabbitMQOrderNotificationRoutingKey, message);

        } catch (Exception e) {
            log.error("Failed to send new order notification to rabbitMQ queue", e);
            throw new OrderEventProducerException("Failed to send new order notification to virtual " +
                    "topic for order: " + orderPlacedEvent.getOrderId(), e);
        }
    }
}
