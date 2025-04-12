package com.springboot.learning.kit.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_details_id")
    private CustomerDetails customerDetails;

    @ManyToOne
    @JoinColumn(name = "customer_address_id")
    private CustomerAddress customerAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "order_created", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderCreated;

    @Column(name = "order_type")
    private OrderType orderType;

}
