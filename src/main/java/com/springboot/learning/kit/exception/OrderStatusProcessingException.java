package com.springboot.learning.kit.exception;

public class OrderStatusProcessingException extends RuntimeException {
    public OrderStatusProcessingException(String message) {
        super(message);
    }
}
