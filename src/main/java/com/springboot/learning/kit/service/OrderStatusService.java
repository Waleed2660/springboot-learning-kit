package com.springboot.learning.kit.service;

import com.springboot.learning.kit.domain.Order;
import com.springboot.learning.kit.domain.OrderItem;
import com.springboot.learning.kit.dto.response.OrderItemStatusResponse;
import com.springboot.learning.kit.dto.response.OrderStatusResponse;
import com.springboot.learning.kit.exception.OrderNotFoundException;
import com.springboot.learning.kit.exception.OrderStatusProcessingException;
import com.springboot.learning.kit.repository.OrderItemRepository;
import com.springboot.learning.kit.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderStatusService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public void saveOrderStatus() {

    }

    public OrderStatusResponse getOrderStatus(Long orderId) {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("No order found with id: " + orderId));
        List<OrderItemStatusResponse> items = transformOrderItems(orderItemRepository.fetchOrderItemsByOrderId(order.getUuid()));
        try {
            return OrderStatusResponse.builder()
                    .orderId(order.getUuid())
                    .items(items)
                    .orderType(String.valueOf(order.getOrderType()))
                    .build();
        } catch (Exception e) {
            throw new OrderStatusProcessingException("Order status processing failure for id: " + orderId);
        }
    }

    private List<OrderItemStatusResponse> transformOrderItems(List<OrderItem> orderItems) {
        try {
            return orderItems.stream().map(item -> OrderItemStatusResponse
                    .builder()
                    .status(item.getStatus())
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .build()).toList();
        } catch (Exception e) {
            throw new OrderStatusProcessingException("Unable to fetch order status for order");
        }
    }
}
