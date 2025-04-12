package com.springboot.learning.kit.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    /**
     * Creates and configures a {@link ConnectionFactory} for RabbitMQ.
     *
     * @return a configured {@link ConnectionFactory} instance
     */
    @Bean
    public ConnectionFactory rabbitConnectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        return factory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

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
        return QueueBuilder.durable("order.placement.queue")
                .withArgument("x-dead-letter-exchange", "dlq.exchange")
                .withArgument("x-dead-letter-routing-key", "order.queue.dlq")
                .build();
    }

    @Bean
    public Queue orderDlq() {
        return QueueBuilder.durable("order.queue.dlq").build();
    }

    @Bean
    public Binding orderQueueBinding() {
        return BindingBuilder.bind(orderQueue()).to(mainExchange()).with("order.queue");
    }

    @Bean
    public Binding orderDlqBinding() {
        return BindingBuilder.bind(orderDlq()).to(dlqExchange()).with("order.queue.dlq");
    }

    // Queue: payment.queue
    @Bean
    public Queue paymentQueue() {
        return QueueBuilder.durable("payment.queue")
                .withArgument("x-dead-letter-exchange", "dlq.exchange")
                .withArgument("x-dead-letter-routing-key", "payment.queue.dlq")
                .build();
    }

    @Bean
    public Queue paymentDlq() {
        return QueueBuilder.durable("payment.queue.dlq").build();
    }

    @Bean
    public Binding paymentQueueBinding() {
        return BindingBuilder.bind(paymentQueue()).to(mainExchange()).with("payment.queue");
    }

    @Bean
    public Binding paymentDlqBinding() {
        return BindingBuilder.bind(paymentDlq()).to(dlqExchange()).with("payment.queue.dlq");
    }
}
