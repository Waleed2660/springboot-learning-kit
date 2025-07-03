package com.springboot.learning.kit.domain;

public enum ItemStatus {
    PROCESSING,
    SHIPPED,
    DELIVERED,
    RETURNED,
    CANCELLED,
    REFUNDED,
    EXCHANGED,
    PENDING,
    COMPLETED;

    public static ItemStatus fromString(String status) {
        for (ItemStatus itemStatus : ItemStatus.values()) {
            if (itemStatus.name().equalsIgnoreCase(status)) {
                return itemStatus;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }
}
