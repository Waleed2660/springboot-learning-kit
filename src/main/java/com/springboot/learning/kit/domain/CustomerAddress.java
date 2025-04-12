package com.springboot.learning.kit.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "customer_address")
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "country")
    private String country;
}
