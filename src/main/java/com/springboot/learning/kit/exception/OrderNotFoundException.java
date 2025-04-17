package com.springboot.learning.kit.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
