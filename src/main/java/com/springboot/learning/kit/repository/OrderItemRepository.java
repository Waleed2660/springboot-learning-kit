package com.springboot.learning.kit.repository;

import com.springboot.learning.kit.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT o FROM OrderItem o WHERE o.orderId = :id")
    List<OrderItem> fetchOrderItemsByOrderId(@Param("id") Long id);
}
