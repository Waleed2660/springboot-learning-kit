package com.springboot.learning.kit.exception;

public class OrderEventProducerException extends RuntimeException {
    public OrderEventProducerException(String message) {
        super(message);
    }

    public OrderEventProducerException(String message, Exception cause) {
        super(message, cause);
    }
}
