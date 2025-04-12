package com.springboot.learning.kit.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_stock")
public class ProductStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private String warehouse;

    private int availableQty;

    private LocalDateTime addedDate;

    private LocalDateTime lastModifiedDate;
}
