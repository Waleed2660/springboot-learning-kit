package com.springboot.learning.kit.unit.service;

import com.springboot.learning.kit.domain.Order;
import com.springboot.learning.kit.dto.request.OrderRequest;
import com.springboot.learning.kit.service.OrderService;
import com.springboot.learning.kit.transformer.OrderTransformer;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderTransformer orderTransformer;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private OrderService orderService;

    @Test
    void saveNewOrder_success() {
        long customerId = 1L;
        long addressId = 2L;
        OrderRequest orderRequest = new OrderRequest();
        Order order = new Order();

        when(orderTransformer.transformOrderRequestToDomain(orderRequest, customerId, addressId)).thenReturn(order);

        // Act
        orderService.saveNewOrder(orderRequest, customerId, addressId);

        // Assert
        verify(orderTransformer).transformOrderRequestToDomain(orderRequest, customerId, addressId);
        verify(entityManager).persist(order);
    }
}
