package com.springboot.learning.kit.repository;

import com.springboot.learning.kit.domain.OrderItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    /**
     * Find all order items belonging to the given orderId
     * @param orderId the order's ID
     * @return list of order items
     */
    List<OrderItem> findByOrderId(Long orderId);
}
