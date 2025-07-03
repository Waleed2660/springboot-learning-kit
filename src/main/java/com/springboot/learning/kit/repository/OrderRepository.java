package com.springboot.learning.kit.repository;

import com.springboot.learning.kit.domain.Order;
import com.springboot.learning.kit.domain.OrderType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Custom query methods can be defined here
    // For example, find orders by status or type
    default List<Order> findByType(OrderType type) {
        return null;
    }
}
