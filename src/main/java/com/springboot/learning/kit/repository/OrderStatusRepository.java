package com.springboot.learning.kit.repository;

import com.springboot.learning.kit.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<Order, Long> {

}
