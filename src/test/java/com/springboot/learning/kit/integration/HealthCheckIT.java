package com.springboot.learning.kit.integration;

import com.springboot.learning.kit.config.TestContainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainersConfig.class)
@ActiveProfiles("test")
public class HealthCheckIT {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void healthCheckShouldReturnOk() {
        String url = String.format("http://localhost:%d/OrderService/management/healthcheck", port);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        System.out.println("Health Check Response: " + response.getBody());
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("""
                ActiveMQ: OK
                RabbitMQ: OK
                Database: OK"""));
    }
}
