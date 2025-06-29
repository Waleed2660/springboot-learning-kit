package com.springboot.learning.kit.config;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

/**
 * Configuration class for setting up ActiveMQ with Spring Boot.
 * This class enables JMS and provides beans for the connection factory and JMS template.
 */
@Configuration
@EnableJms
public class ActiveMQConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerURL;

    @Value("${spring.activemq.user}")
    private String brokerUsername;

    @Value("${spring.activemq.password}")
    private String brokerPassword;

    /**
     * Creates and configures a {@link ConnectionFactory} for ActiveMQ.
     *
     * @return a configured {@link ConnectionFactory} instance
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerURL);
        factory.setUserName(brokerUsername);
        factory.setPassword(brokerPassword);
        return factory;
    }

    /**
     * Creates and configures a {@link JmsTemplate} for sending and receiving messages.
     *
     * @param connectionFactory the {@link ConnectionFactory} to be used by the {@link JmsTemplate}
     * @return a configured {@link JmsTemplate} instance
     */
    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        return new JmsTemplate(connectionFactory);
    }
}
