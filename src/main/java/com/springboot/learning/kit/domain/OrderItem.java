package com.springboot.learning.kit.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Getter
@Builder
@Table(name = "order_items")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status")
    private String status;

    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;
}
