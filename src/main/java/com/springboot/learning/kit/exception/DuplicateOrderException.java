package com.springboot.learning.kit.exception;

public class DuplicateOrderException extends RuntimeException {
    public DuplicateOrderException(String message) {
        super(message);
    }

    public DuplicateOrderException(String message, Exception cause) {
        super(message, cause);
    }
}
