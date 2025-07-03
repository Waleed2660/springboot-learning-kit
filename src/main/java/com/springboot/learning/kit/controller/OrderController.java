package com.springboot.learning.kit.controller;

import com.springboot.learning.kit.dto.request.OrderRequest;
import com.springboot.learning.kit.exception.DuplicateOrderException;
import com.springboot.learning.kit.exception.OrderNotFoundException;
import com.springboot.learning.kit.exception.OrderValidationException;
import com.springboot.learning.kit.service.OrderProcessingService;
import com.springboot.learning.kit.service.OrderStatusService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProcessingService orderProcessingService;
    private final OrderStatusService orderStatusService;

    /**
     * Endpoint to submit an order for processing.
     *
     * @param orderRequest the order to be processed
     * @return a ResponseEntity indicating the result of the operation
     */
    @PostMapping("/submit")
    public ResponseEntity<?> submitOrder(@RequestBody OrderRequest orderRequest) {
        try {
            orderProcessingService.processNewOrder(orderRequest);
            return ResponseEntity.ok("Order submitted successfully");
        } catch (OrderValidationException e) {
            log.error("Order validation failed: {} ~ ", orderRequest.getUUID(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        } catch (DuplicateOrderException e) {
            log.error("Order already exists in the DB: {} ~ ", orderRequest.getUUID(), e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            log.error("Error processing order: {} ~ ", orderRequest.getUUID(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Encountered error while processing order: " + orderRequest.getUUID()));
        }
    }

    @GetMapping("/status/{orderId}")
    public ResponseEntity<?> getOrderStatus(@PathVariable Long orderId) {
        try {
            return ResponseEntity.ok(orderStatusService.getOrderStatus(orderId));
        } catch (OrderNotFoundException e) {
            log.error("Order details not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            log.error("Error retrieving order status: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Unable to retrieve order status"));
        }
    }
}
