package com.springboot.learning.kit.exception;

public class OrderProcessingException extends RuntimeException {
    public OrderProcessingException(String message) {
        super(message);
    }

    public OrderProcessingException(String message, Exception cause) {
        super(message, cause);
    }
}
