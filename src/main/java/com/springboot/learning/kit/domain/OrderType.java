package com.springboot.learning.kit.domain;

import java.util.List;

public enum OrderType {
    ONLINE,
    OFFLINE,
    IN_STORE;

    public static List<OrderType> getAllOrderTypes() {
        return List.of(OrderType.values());
    }
}
