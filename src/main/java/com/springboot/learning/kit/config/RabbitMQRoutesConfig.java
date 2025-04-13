package com.springboot.learning.kit.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQRoutesConfig {

    @Value("${rmq.order.placement.queue}")
    private String orderPlacementQueue;

    @Value("${rmq.order.placement.queue.dlq}")
    private String orderPlacementQueueDlq;

    @Value("${rmq.order.status.queue}")
    private String orderStatusQueue;

    @Value("${rmq.order.status.queue.dlq}")
    private String orderStatusQueueDlq;

    @Value("${rmq.order.cancellation.queue}")
    private String orderCancellationQueue;

    @Value("${rmq.order.cancellation.queue.dlq}")
    private String orderCancellationQueueDlq;

    /**
     * This queue is used to check the health of the RabbitMQ server.
     */
    @Bean
    public Queue healthCheckQueue() {
        return new Queue("healthcheck", true); // durable queue
    }

    // Define exchanges
    @Bean
    public TopicExchange mainExchange() {
        return new TopicExchange("main.exchange");
    }

    @Bean
    public TopicExchange dlqExchange() {
        return new TopicExchange("dlq.exchange");
    }

    // Queue: order.queue
    @Bean
    public Queue orderQueue() {
        return QueueBuilder.durable("rmq.order.placement.queue")
                .withArgument("x-dead-letter-exchange", "dlq.exchange")
                .withArgument("x-dead-letter-routing-key", "order.queue.dlq")
                .build();
    }

    @Bean
    public Queue orderDlq() {
        return QueueBuilder.durable("rmq.order.placement.queue.dlq").build();
    }

    @Bean
    public Binding orderQueueBinding() {
        return BindingBuilder.bind(orderQueue()).to(mainExchange()).with("order.queue");
    }

    @Bean
    public Binding orderDlqBinding() {
        return BindingBuilder.bind(orderDlq()).to(dlqExchange()).with("order.queue.dlq");
    }

}
