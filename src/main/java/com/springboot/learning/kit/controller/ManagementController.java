package com.springboot.learning.kit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Objects;

@RestController
@RequestMapping("/management")
@RequiredArgsConstructor
public class ManagementController {

    private final JmsTemplate jmsTemplate;
    private final DataSource dataSource;

    /**
     * Health check endpoint to verify the status of ActiveMQ and database connection.
     *
     * @return a ResponseEntity containing the health status
     */
    @GetMapping("/healthcheck")
    public ResponseEntity<String> healthCheck() {
        StringBuilder healthStatus = new StringBuilder();

        // Check ActiveMQ
        try {
            Objects.requireNonNull(jmsTemplate.getConnectionFactory()).createConnection().close();
            healthStatus.append("ActiveMQ: OK\n");
        } catch (Exception e) {
            healthStatus.append("ActiveMQ: DOWN\n");
        }

        // Check Database
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(2)) {
                healthStatus.append("Database: OK\n");
            } else {
                healthStatus.append("Database: DOWN\n");
            }
        } catch (Exception e) {
            healthStatus.append("Database: DOWN\n");
        }

        // Return health status
        if (healthStatus.toString().contains("DOWN")) {
            return new ResponseEntity<>(healthStatus.toString(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>(healthStatus.toString(), HttpStatus.OK);
    }
}
