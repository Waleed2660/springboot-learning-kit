package com.springboot.learning.kit.integration;

import com.springboot.learning.kit.config.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class HealthCheckIT extends BaseIntegrationTest {

    @Test
    void healthCheckShouldReturnOk() {
        String url = getBaseUrl() + "/management/healthcheck";
        System.out.println("Health Check URL: " + url);
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
