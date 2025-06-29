package com.springboot.learning.kit.routes;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQRoutes {

    public static final String EXCHANGE = "order.exchange";
    public static final String DLQ_EXCHANGE = "dlq.exchange";

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

    @Bean
    public Queue healthCheckQueue() {
        return new Queue("healthcheck", true); // durable queue
    }

    @Bean
    public TopicExchange dlqExchange() {
        return new TopicExchange("dlq.exchange");
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue mainQueue() {
        return QueueBuilder.durable(orderPlacementQueue)
                .withArgument("x-dead-letter-exchange", DLQ_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", orderPlacementQueueDlq)
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(orderPlacementQueueDlq).build();
    }

    @Bean
    public Binding mainBinding(Queue mainQueue, DirectExchange exchange) {
        return BindingBuilder.bind(mainQueue).to(exchange).with(orderPlacementQueue);
    }

    @Bean
    public Binding dlqBinding(Queue deadLetterQueue, TopicExchange dlqExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(dlqExchange).with(orderPlacementQueueDlq);
    }
}
