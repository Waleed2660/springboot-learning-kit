package com.springboot.learning.kit.service;

import com.springboot.learning.kit.domain.Order;
import com.springboot.learning.kit.domain.OrderItem;
import com.springboot.learning.kit.dto.response.OrderItemStatusResponse;
import com.springboot.learning.kit.dto.response.OrderStatusResponse;
import com.springboot.learning.kit.exception.OrderNotFoundException;
import com.springboot.learning.kit.repository.OrderItemRepository;
import com.springboot.learning.kit.repository.OrderRepository;
import io.micrometer.core.annotation.Timed;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStatusService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Timed(value = "read.order.status", description = "Time taken to retrieve order status from database")
    public OrderStatusResponse getOrderStatus(Long orderUUID) {

        // first fetch the order from the repository
        Order order = orderRepository
                .findById(orderUUID)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with UUID: " + orderUUID));

        // then fetch the order items associated with the order
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderUUID);

        if (orderItems.isEmpty()) {
            throw new OrderNotFoundException("No order items found for order with UUID: " + orderUUID);
        }

        return OrderStatusResponse.builder()
                .orderId(orderUUID)
                .orderType(order.getOrderType().name())
                .items(convertEntityToDTO(orderItems))
                .build();
    }

    private List<OrderItemStatusResponse> convertEntityToDTO(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem -> OrderItemStatusResponse.builder()
                        .productId(orderItem.getProductId())
                        .quantity(orderItem.getQuantity())
                        .status(orderItem.getStatus())
                        .build())
                .toList();
    }
}
