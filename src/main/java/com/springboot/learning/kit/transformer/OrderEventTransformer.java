package com.springboot.learning.kit.transformer;

import com.springboot.learning.kit.domain.OrderType;
import com.springboot.learning.kit.event.OrderEventType;
import com.springboot.learning.kit.event.OrderPlacedEvent;
import com.springboot.learning.kit.dto.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OrderEventTransformer {

    public OrderPlacedEvent transformToOrderPlacedEvent(OrderRequest orderRequest) {
        return OrderPlacedEvent.builder()
                .orderId(orderRequest.getUUID())
                .orderType(OrderType.valueOf(orderRequest.getOrderType()).name())
                .eventType(OrderEventType.ORDER_PLACED.name())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
