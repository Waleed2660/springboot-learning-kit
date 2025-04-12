package com.springboot.learning.kit.repository;

import com.springboot.learning.kit.domain.Order;
import com.springboot.learning.kit.domain.OrderStatus;
import com.springboot.learning.kit.domain.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Custom query methods can be defined here
    // For example, find orders by status or type
    default List<Order> findByType(OrderType type) {
        return null;
    }
}
