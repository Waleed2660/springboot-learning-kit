package com.springboot.learning.kit.service;

import com.springboot.learning.kit.domain.OrderItem;
import com.springboot.learning.kit.dto.request.OrderItemRequest;
import com.springboot.learning.kit.repository.OrderItemRepository;
import com.springboot.learning.kit.transformer.OrderTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderTransformer orderTransformer;
    private final OrderItemRepository orderItemRepository;

    public void saveOrderItems(List<OrderItemRequest> orderItem, long orderUUID) {
        List<OrderItem> orderItemEntities = orderTransformer.transformOrderItemRequestToDomain(orderItem, orderUUID);
        for (OrderItem orderItemEntity : orderItemEntities) {
            orderItemRepository.save(orderItemEntity);
        }
    }

}

