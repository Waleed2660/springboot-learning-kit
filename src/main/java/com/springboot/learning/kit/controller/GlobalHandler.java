package com.springboot.learning.kit.controller;


import com.springboot.learning.kit.exception.OrderNotFoundException;
import com.springboot.learning.kit.exception.OrderStatusProcessingException;
import com.springboot.learning.kit.exception.OrderValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(OrderValidationException.class)
    public ResponseEntity<String> handleOrderValidationException(OrderValidationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(OrderStatusProcessingException.class)
    public ResponseEntity<String> handleOrderStatusProcessingException(OrderStatusProcessingException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}
