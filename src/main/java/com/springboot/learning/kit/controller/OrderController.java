package com.springboot.learning.kit.controller;

import com.springboot.learning.kit.dto.request.OrderRequest;
import com.springboot.learning.kit.dto.response.OrderStatusResponse;
import com.springboot.learning.kit.exception.OrderValidationException;
import com.springboot.learning.kit.service.OrderProcessingService;
import com.springboot.learning.kit.service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> submitOrder(@RequestBody OrderRequest orderRequest) {
        orderProcessingService.processNewOrder(orderRequest);
        return ResponseEntity.ok("Order submitted successfully");
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<OrderStatusResponse> getOrderStatus(@PathVariable Long id) {
        return ResponseEntity.ok(orderStatusService.getOrderStatus(id));
    }
}
