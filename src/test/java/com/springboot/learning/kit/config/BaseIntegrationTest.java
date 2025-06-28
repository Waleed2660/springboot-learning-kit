package com.springboot.learning.kit.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.activemq.ActiveMQContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@Testcontainers
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest {

    /* -------------------------------------------------------------------- */
    /* ⇢ HTTP helpers                                                       */
    /* -------------------------------------------------------------------- */

    @LocalServerPort
    private Integer port;

    protected RestTemplate restTemplate = new RestTemplate();

    protected String getBaseUrl() {
        if (port == null || port == 0) {
            throw new IllegalStateException("Port for Base URL has not been injected yet. Check BaseIntegrationTest class");
        }
        return "http://localhost:" + port + "/OrderService";
    }

    /* -------------------------------------------------------------------- */
    /* ⇢ Shared Testcontainers                                              */
    /* -------------------------------------------------------------------- */

    @PersistenceContext
    protected EntityManager entityManager;

    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("test_db")
            .withUsername("test")
            .withPassword("test");

    private static final RabbitMQContainer rabbitmq = new RabbitMQContainer(
            DockerImageName.parse("rabbitmq:latest")
    );

    private static final ActiveMQContainer activemq = new ActiveMQContainer(
            DockerImageName.parse("apache/activemq-classic:latest")
    );

    /* -------------------------------------------------------------------- */
    /* ⇢ Wire container properties into Spring                              */
    /* -------------------------------------------------------------------- */

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
