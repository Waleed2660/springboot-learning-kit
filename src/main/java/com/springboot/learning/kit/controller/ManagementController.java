package com.springboot.learning.kit.controller;

import java.sql.Connection;
import java.util.Objects;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
@RequiredArgsConstructor
public class ManagementController {

    private final JmsTemplate jmsTemplate;
    private final DataSource dataSource;
    private final RabbitTemplate rabbitTemplate;

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
            Objects.requireNonNull(jmsTemplate.getConnectionFactory())
                    .createConnection()
                    .close();
            healthStatus.append("ActiveMQ: OK\n");
        } catch (Exception e) {
            healthStatus.append("ActiveMQ: DOWN\n");
        }

        // Check RabbitMQ
        try {
            rabbitTemplate.execute(channel -> {
                channel.basicPublish("", "healthcheck", null, "ping".getBytes());
                return null;
            });
            healthStatus.append("RabbitMQ: OK\n");
        } catch (Exception e) {
            healthStatus.append("RabbitMQ: DOWN\n");
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
