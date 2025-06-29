package com.springboot.learning.kit.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Getter
@Builder
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Column(name = "uuid")
    private Long uuid;

    @Column(name = "order_type")
    private OrderType orderType;

    @JoinColumn(name = "customer_details_id")
    private Long customerDetailsId;

    @JoinColumn(name = "customer_address_id")
    private Long customerAddressId;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "order_created")
    private LocalDateTime orderCreated;
}
