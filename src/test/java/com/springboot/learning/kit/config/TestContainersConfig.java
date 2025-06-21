package com.springboot.learning.kit.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.activemq.ActiveMQContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@TestConfiguration
public class TestContainersConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test_db")
            .withUsername("test")
            .withPassword("test");

    private static final RabbitMQContainer rabbitmq = new RabbitMQContainer(
            DockerImageName.parse("rabbitmq:latest")
    );

    private static final ActiveMQContainer activemq = new ActiveMQContainer(
            DockerImageName.parse("apache/activemq-classic:latest")
    );

    static {
        postgres.start();
        rabbitmq.start();
        activemq.start();

        // Set system properties for test containers
        System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgres.getUsername());
        System.setProperty("spring.datasource.password", postgres.getPassword());
        System.setProperty("spring.datasource.driver-class-name", postgres.getDriverClassName());

        System.setProperty("spring.rabbitmq.host", rabbitmq.getHost());
        System.setProperty("spring.rabbitmq.port", String.valueOf(rabbitmq.getMappedPort(5672)));
        System.setProperty("spring.rabbitmq.username", rabbitmq.getAdminUsername());
        System.setProperty("spring.rabbitmq.password", rabbitmq.getAdminPassword());

        System.setProperty("spring.activemq.broker-url", activemq.getBrokerUrl());
        System.setProperty("spring.activemq.user", "admin");
        System.setProperty("spring.activemq.password", "admin");
    }
}
