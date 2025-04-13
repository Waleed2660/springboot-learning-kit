package com.springboot.learning.kit.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Table(name = "product_stock")
@NoArgsConstructor
@AllArgsConstructor
public class ProductStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "warehouse")
    private String warehouse;

    @Column(name = "available_qty")
    private int availableQty;

    @Column(name = "added_date")
    private LocalDateTime addedDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;
}
